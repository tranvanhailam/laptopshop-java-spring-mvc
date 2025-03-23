package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class HomePageController {

    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(Model model, HttpServletRequest request) {
        List<Product> productList = this.productService.getAllProducts();
        model.addAttribute("productList", productList);
        HttpSession session = request.getSession(false);
        
        return "client/homepage/show";
    }

    @RequestMapping(value = "/access-deny", method = RequestMethod.GET)
    public String getAccessDenyPage(Model model) {
        return "client/auth/access-deny";
    }

}
