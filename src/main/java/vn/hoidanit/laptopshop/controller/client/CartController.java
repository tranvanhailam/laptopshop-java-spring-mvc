package vn.hoidanit.laptopshop.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.CartDetailService;
import vn.hoidanit.laptopshop.service.CartService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class CartController {

    private final CartDetailService cartDetailService;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartDetailService cartDetailService, UserService userService, CartService cartService,
            ProductService productService) {
        this.cartDetailService = cartDetailService;
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getCartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User user = this.userService.getFirstUserByEmail(email);
        Cart cart = user.getCart();
        List<CartDetail> cartDetailList = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetailList) {
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        }
        model.addAttribute("cartDetailList", cartDetailList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }

    @RequestMapping(value = "/product/add-product-to-cart/{id}", method = RequestMethod.POST)
    public String addProductToCart(Model model, @PathVariable long id,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, session, id, 1);
        return "redirect:/";
    }

    @RequestMapping(value = "/product/add-product-to-cart-from-view-detail/{id}", method = RequestMethod.POST)
    public String addProductToCartFromViewDetail(Model model, @PathVariable long id,
            @RequestParam("quantity") int quantity,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, session, id, quantity);
        return "redirect:/product/detail/" + id;
    }

    @RequestMapping(value = "/delete-cart-product/{id}", method = RequestMethod.POST)
    public String deleteCartProduct(Model model, HttpServletRequest request, @PathVariable long id) {
        HttpSession session = request.getSession(false);
        this.cartDetailService.handleRemoveCartDetail(id, session);
        return "redirect:/cart";
    }

}
