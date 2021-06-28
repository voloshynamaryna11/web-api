package genesis.school.webapi.controller;

import genesis.school.webapi.exception.DataProcessingException;
import genesis.school.webapi.file_repository.UserFileRepository;
import genesis.school.webapi.model.BTCDto;
import genesis.school.webapi.model.User;
import genesis.school.webapi.model.UserRequestLoginDto;
import genesis.school.webapi.model.UserRequestRegistrationDto;
import genesis.school.webapi.util.HashUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class MainController {
    private static final String uri = "https://api.cryptonator.com/api/full/btc-uah";
    private UserFileRepository userFileRepository;

    public MainController(UserFileRepository userFileRepository) {
        this.userFileRepository = userFileRepository;
    }

    @RequestMapping(
            value = "/btcRate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BTCDto getBtcRate(HttpServletRequest request) throws ParseException {
        if (request.getSession().getAttribute("id") == null) throw new DataProcessingException("Login to get info");
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(uri, String.class);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(res);
        JSONObject ticker = (JSONObject) jsonObject.get("ticker");
        BTCDto btcDto = new BTCDto(ticker.get("base").toString(), ticker.get("target").toString(), Double.parseDouble(ticker.get("price").toString()));
        return btcDto;
    }

    @PostMapping("/user/create")
    public void registerUser(@Valid @RequestBody UserRequestRegistrationDto userRequestRegistrationDto) {
        userFileRepository.save(userRequestRegistrationDto);
    }

    @PostMapping("/user/login")
    public void loginUser(@Valid @RequestBody UserRequestLoginDto userRequestLoginDto, HttpServletRequest request) {
        Optional<User> user = userFileRepository.getByEmail(userRequestLoginDto.getEmail());
        if (user.isPresent() && user.get().getPassword()
                .equals(HashUtil.hashPassword(userRequestLoginDto.getPassword(), user.get().getSalt()))) {
            request.getSession().setAttribute("id", user.get().getId());
        } else {
            throw new DataProcessingException("Wrong password or login");
        }
    }
}
