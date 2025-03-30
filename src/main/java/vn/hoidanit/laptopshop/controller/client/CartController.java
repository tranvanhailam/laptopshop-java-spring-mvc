package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getCartPage() {
        return "client/cart/show";
    }

}
