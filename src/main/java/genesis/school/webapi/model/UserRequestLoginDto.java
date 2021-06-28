package genesis.school.webapi.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestLoginDto {
    @NotNull(message = "Email must be specified")
    @Size(min = 1, message = "Email must be specified")
    private String email;
    @NotNull(message = "Password name must be specified")
    @Size(min = 1, message = "Password name must be specified")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
