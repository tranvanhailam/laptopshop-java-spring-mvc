package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.RoleService;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;// interface trong Spring Security, thường được sử dụng để mã hóa
                                                  // (encode) hoặc xác minh (verify) mật khẩu.

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder, RoleService roleService) {// Tạo DI
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    // Table User
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String getTableUserPage(Model model) {
        List<User> userList = this.userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "admin/user/show";
    }

    // Detail User
    @RequestMapping(value = "/admin/user/detail/{id}", method = RequestMethod.GET)
    public String getDetailUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    // Before Update User
    @RequestMapping(value = "/admin/user/update/{id}", method = RequestMethod.GET)
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/update";
    }

    // After Update User
    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public String getTableUserPageAfterUpdate(Model model, @ModelAttribute("user") @Valid User user,
            BindingResult userUpdatedBindingResult,
            @RequestParam("avatarFile") MultipartFile avatarFile) {

        if (userUpdatedBindingResult.hasErrors())
            return "admin/user/update";

        User userUpdated = this.userService.getUserById(user.getId());
        if (userUpdated != null) {
            userUpdated.setEmail(user.getEmail());
            userUpdated.setPassword(user.getPassword());
            userUpdated.setFullName(user.getFullName());
            userUpdated.setPhone(user.getPhone());
            userUpdated.setAddress(user.getAddress());
            userUpdated.setRole(this.roleService.getRoleByName(user.getRole().getName()));

            if (!avatarFile.isEmpty()) {
                this.uploadService.handleDeleteFile(userUpdated.getAvatar(), "avatar");
                String avatarFileName = this.uploadService.handleSaveUploadFile(avatarFile, "avatar");// Save file
                userUpdated.setAvatar(avatarFileName);
            }

            this.userService.handleSaveUser(userUpdated);
        }
        return "redirect:/admin/user";
    }

    // Before Delete User
    @RequestMapping(value = "admin/user/delete/{id}", method = RequestMethod.GET)
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/delete";
    }

    // After Delete User
    @RequestMapping(value = "admin/user/delete", method = RequestMethod.POST)
    public String getTableUserPageAfterDelete(Model model, @ModelAttribute("user") User user) {
        User userDeleted = this.userService.getUserById(user.getId());
        this.uploadService.handleDeleteFile(userDeleted.getAvatar(), "avatar");
        this.userService.deleteUserByID(userDeleted.getId());
        return "redirect:/admin/user";
    }

    // Before Create User
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.GET)
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());// Thêm thuộc tính
        return "admin/user/create";
    }

    // After Create User
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String getTableUserPageAfterCreate(Model model,
            @ModelAttribute("newUser") @Valid User user,
            BindingResult newUserBindingResult,
            @RequestParam("avatarFile") MultipartFile avatarFile) {

        // List<FieldError> errors = newUserBindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println("------------------" + error.getField() + " - " +
        // error.getDefaultMessage());
        // }

        if (newUserBindingResult.hasErrors())
            return "admin/user/create";

        String avatarFileName = this.uploadService.handleSaveUploadFile(avatarFile, "avatar");// Save file
        String hashPassword = this.passwordEncoder.encode(user.getPassword());// hash password

        user.setAvatar(avatarFileName);
        user.setPassword(hashPassword);
        user.setRole(this.roleService.getRoleByName(user.getRole().getName()));

        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

}
