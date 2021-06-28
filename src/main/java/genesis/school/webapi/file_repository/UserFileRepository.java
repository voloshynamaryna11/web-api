package genesis.school.webapi.file_repository;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import genesis.school.webapi.exception.DataProcessingException;
import genesis.school.webapi.mapper.UserMapper;
import genesis.school.webapi.model.User;
import genesis.school.webapi.model.UserRequestRegistrationDto;
import genesis.school.webapi.util.HashUtil;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserFileRepository {
    private UserMapper userMapper;

    public UserFileRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void save(UserRequestRegistrationDto userRequestRegistrationDto) {
        if (getByEmail(userRequestRegistrationDto.getEmail()).isEmpty()) {

            byte[] salt = HashUtil.getSalt();
            String saltedPassword = HashUtil.hashPassword(userRequestRegistrationDto.getPassword(), salt);

            List<User> users = getAll();
            User user = new User(users.size() == 0 ? 1 : users.size() + 1, userRequestRegistrationDto.getFirstName(),
                    userRequestRegistrationDto.getLastName(), userRequestRegistrationDto.getEmail(),
                    saltedPassword, salt);

            try {
                CSVWriter writer = new CSVWriter(new FileWriter("src\\main\\resources\\users", true));
                writer.writeNext(userMapper.mapFromUserToString(user));
                writer.close();
            } catch (IOException e) {
                throw new DataProcessingException("Can't register user");
            }
        } else {
            throw new DataProcessingException("This user is already registered");
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("src\\main\\resources\\users"), ',', '"', 1)) {
            List<String[]> allRows = reader.readAll();
            for (String[] row : allRows) {
                users.add(userMapper.mapFromStringToUser(row));
            }
        } catch (IOException e) {
            throw new DataProcessingException("Can't establish connection with users info");
        }
        return users;
    }

    public Optional<User> getByEmail(String email) {
        return getAll().stream().filter(user1 -> user1.getEmail().equals(email)).findFirst();
    }
}
