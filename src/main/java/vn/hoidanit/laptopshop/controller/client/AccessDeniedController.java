package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessDeniedController {
    @RequestMapping(value = "/access-deny", method = RequestMethod.GET)
    public String getAccessDenyPage(Model model) {
        return "client/auth/access-deny";
    }
}
