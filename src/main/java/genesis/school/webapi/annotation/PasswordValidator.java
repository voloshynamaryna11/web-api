package genesis.school.webapi.annotation;

import genesis.school.webapi.model.UserRequestRegistrationDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, UserRequestRegistrationDto> {
    @Override
    public boolean isValid(UserRequestRegistrationDto userRequestRegistrationDto, ConstraintValidatorContext constraintValidatorContext) {
        return userRequestRegistrationDto.getPassword() != null && userRequestRegistrationDto.getPassword()
                .equals(userRequestRegistrationDto.getRepeatPassword());
    }
}
