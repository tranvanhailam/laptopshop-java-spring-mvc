package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class DashboardController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    public DashboardController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getDashboardPage(Model model) {

        int numberUser = this.userService.getAllUsers().size();
        int numberProduct = this.productService.getAllProducts().size();
        int numberOrder = this.orderService.getAllOrders().size();

        model.addAttribute("numberUser", numberUser);
        model.addAttribute("numberProduct", numberProduct);
        model.addAttribute("numberOrder", numberOrder);

        return "admin/dashboard/show";
    }
}
