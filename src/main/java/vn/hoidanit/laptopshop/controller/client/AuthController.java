package vn.hoidanit.laptopshop.controller.client;

import java.net.PasswordAuthentication;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.auth.RegisterDTO;
import vn.hoidanit.laptopshop.service.AuthService;
import vn.hoidanit.laptopshop.service.RoleService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthController(AuthService authService, UserService userService, PasswordEncoder passwordEncoder,
            RoleService roleService) {
        this.authService = authService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    // Before register
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());//
        return "client/auth/register";
    }

    // After register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String getLoginPageAfterRegister(Model model, @ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
            BindingResult userRegisterBindingResult) {

        List<FieldError> errors = userRegisterBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println("------------------" + error.getField() + " - " +
                    error.getDefaultMessage());
        }

        if (userRegisterBindingResult.hasErrors())
            return "client/auth/register";

        User user = authService.mapperClassRegisterDTOToUser(registerDTO);

        String hashPassword = this.passwordEncoder.encode(user.getPassword());// hash password

        user.setPassword(hashPassword);
        user.setRole(this.roleService.getRoleByName("USER"));

        this.userService.handleSaveUser(user);

        return "redirect:/login";
    }

    // Before login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }

    // After login
    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // public String getHomePageAfterLogin(Model model, @ModelAttribute("loginUser") @Valid LoginDTO loginDTO,
    //         BindingResult userLoginBindingResult) {

    //     List<FieldError> errors = userLoginBindingResult.getFieldErrors();
    //     for (FieldError error : errors) {
    //         System.out.println("------------------" + error.getField() + " - " +
    //                 error.getDefaultMessage());
    //     }

    //     if (userLoginBindingResult.hasErrors())
    //         return "client/auth/login";

    //     return "redirect:/";
    // }
}
