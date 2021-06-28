package genesis.school.webapi.model;

import genesis.school.webapi.annotation.EmailConstraint;
import genesis.school.webapi.annotation.PasswordConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordConstraint
public class UserRequestRegistrationDto {
    @NotNull(message = "First name must be specified")
    @Size(min = 2, message = "First name must be at least 2 characters")
    private String firstName;
    @NotNull(message = "Last name must be specified")
    @Size(min = 2, message = "Last name must be at least 2 characters")
    private String lastName;
    @EmailConstraint
    private String email;
    @NotNull(message = "Password must be specified")
    @Size(min = 5, message = "Password has to have at least 5 characters")
    private String password;
    @NotNull(message = "Repeating password must be specified")
    @Size(min = 5, message = "Repeating password has to have at least 5 characters")
    private String repeatPassword;

    public UserRequestRegistrationDto(@NotNull(message = "First name must be specified") @Size(min = 2, message = "First name must be at least 2 characters") String firstName, @NotNull(message = "Last name must be specified") @Size(min = 2, message = "Last name must be at least 2 characters") String lastName, String email, @NotNull(message = "Password must be specified") @Size(min = 5, message = "Password has to have at least 5 characters") String password, @NotNull(message = "Repeating password must be specified") @Size(min = 5, message = "Repeating password has to have at least 5 characters") String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
