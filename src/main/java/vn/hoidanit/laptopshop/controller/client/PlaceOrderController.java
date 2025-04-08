package vn.hoidanit.laptopshop.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.order.ReceiverInfoDTO;
import vn.hoidanit.laptopshop.service.CartDetailService;
import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class PlaceOrderController {

    private final CartDetailService cartDetailService;
    private final UserService userService;
    private final OrderService orderService;

    public PlaceOrderController(CartDetailService cartDetailService, UserService userService,
            OrderService orderService) {
        this.cartDetailService = cartDetailService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/confirm-checkout", method = RequestMethod.POST)
    public String confirmCheckout(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetailList = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.cartDetailService.handleUpdateCartDetailBeforeCheckout(cartDetailList);
        return "redirect:/checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String getCheckoutPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User user = this.userService.getFirstUserByEmail(email);
        Cart cart = user.getCart();
        List<CartDetail> cartDetailList = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetailList) {
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        }

        ReceiverInfoDTO receiverInfoDTO = new ReceiverInfoDTO();
        receiverInfoDTO.setFullName(user.getFullName());
        receiverInfoDTO.setAddress(user.getAddress());
        receiverInfoDTO.setPhoneNumber(user.getPhone());

        model.addAttribute("cartDetailList", cartDetailList);
        model.addAttribute("receiverInfoDTO", receiverInfoDTO);
        model.addAttribute("totalPrice", totalPrice);
        return "client/cart/checkout";
    }

    @RequestMapping(value = "/place-order", method = RequestMethod.POST)
    public String handlePlaceOrder(
            HttpServletRequest request,
            @ModelAttribute("receiverInfoDTO") ReceiverInfoDTO receiverInfoDTO) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User user = this.userService.getFirstUserByEmail(email);

        this.orderService.handlePlaceOrder(user, session, receiverInfoDTO);

        return "redirect:/thanks";
    }

    @RequestMapping(value = "/thanks", method = RequestMethod.GET)
    public String getThanksPage(Model model) {
        return "client/cart/thanks";
    }

    @RequestMapping(value = "/order-history", method = RequestMethod.GET)
    public String getOrderHistoryPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User user = this.userService.getFirstUserByEmail(email);
        List<Order> orderList = this.orderService.getOrderByUser(user);
        model.addAttribute("orderList", orderList);
        return "client/cart/order-history";
    }

}
