package vn.hoidanit.laptopshop.service.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.controller.client.AuthController;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.dto.auth.RegisterDTO;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

    private final AuthController authController;

    private final UserService userService;

    public RegisterValidator(UserService userService, AuthController authController) {
        this.userService = userService;
        this.authController = authController;
    }

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check first name
        if (user.getFirstName().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Tên không được bỏ trống")
                    .addPropertyNode("firstName")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Check last name
        if (user.getFirstName().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Họ không được bỏ trống")
                    .addPropertyNode("lastName")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Check Email

        if (user.getEmail().isEmpty()) {
            context.buildConstraintViolationWithTemplate("Email không được bỏ trống")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (!Pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email không hợp lệ")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (this.userService.checkEmailExists(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email " + user.getEmail() + " đã được đăng ký")
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

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Mật khẩu không khớp")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
