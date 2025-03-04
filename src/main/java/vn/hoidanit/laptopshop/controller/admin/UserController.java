package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {// Tạo DI
        this.userService = userService;
    }

    // Index
    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> userList = this.userService.getAllUsersByEmail("hailamtranvan@gmail.com");
        model.addAttribute("userList", userList);// Thêm thuộc tính
        return "index";
    }

    // Table User
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String getTableUserPage(Model model) {
        List<User> userList = this.userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "admin/user/show";
    }

    // Detail User
    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    public String getUserDetailPage(Model model, @PathVariable long id) {
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
    public String getTableUserPageAfterUpdate(Model model, @ModelAttribute("user") User user) {
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setFullName(user.getFullName());
            currentUser.setAddress(user.getAddress());
            currentUser.setPhone(user.getPhone());
            this.userService.handleSaveUser(currentUser);
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
        this.userService.deleteUserByID(user.getId());
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
    public String getTableUserPageAfterCreate(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user"; // Map to method @RequestMapping(value = "/admin/user", method =
                                       // RequestMethod.GET)
    }

}
