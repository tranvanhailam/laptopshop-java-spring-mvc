package vn.hoidanit.laptopshop.service.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.dto.auth.LoginDTO;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class LoginValidator implements ConstraintValidator<LoginChecked, LoginDTO> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginValidator(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isValid(LoginDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check Email

        if (user.getEmail().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Email không được bỏ trống")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Check password

        if (user.getPassword().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Mật khẩu không được bỏ trống")
                    .addPropertyNode("password")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Check

        if (!this.userService.checkEmailExists(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email " + user.getEmail() + " chưa được đăng ký")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        } else {
            String hashPassword = this.userService.getFirstUserByEmail(user.getEmail()).getPassword();
            if (!this.passwordEncoder.matches(user.getPassword(), hashPassword)) {
                context.buildConstraintViolationWithTemplate("Sai mật khẩu")
                        .addPropertyNode("password")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
        }

        return valid;
    }

}
