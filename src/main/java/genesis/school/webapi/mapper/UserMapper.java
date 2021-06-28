package genesis.school.webapi.mapper;

import genesis.school.webapi.model.User;
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class UserMapper {
    public User mapFromStringToUser(String[] user) {
        return new User(Integer.parseInt(user[0]), user[1], user[2], user[3], user[4], Base64.getDecoder().decode(user[5]));
    }

    public String[] mapFromUserToString(User user) {
        return user.toString().split(",");
    }
}
