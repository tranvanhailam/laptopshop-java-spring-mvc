package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String grtHomePage(Model model) {
        return "client/homepage/show";
    }

}
