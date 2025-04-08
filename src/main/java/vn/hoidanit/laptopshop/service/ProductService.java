package vn.hoidanit.laptopshop.service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.order.ReceiverInfoDTO;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final UserService userService;
    private final CartDetailService cartDetailService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public ProductService(ProductRepository productRepository, CartService cartService, UserService userService,
            CartDetailService cartDetailService, OrderService orderService, OrderDetailService orderDetailService) {
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.cartDetailService = cartDetailService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public void deleteProductByID(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long id,int quantity, HttpSession session) {
        User user = this.userService.getFirstUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartService.getCartByUser(user);
            if (cart == null) {// If user have not cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);
                cart = this.cartService.handleSaveCart(newCart);
            }

            Product product = this.productRepository.findById(id);
            if (product != null) {

                CartDetail cartDetail = this.cartDetailService.getCartDetailByCartAndProduct(cart, product);

                if (cartDetail != null) { // when product in cart
                    cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
                    this.cartDetailService.handleSaveCartDetail(cartDetail);

                } else {// when product not in cart
                    //new CartDetail
                    CartDetail newCartDetail = new CartDetail();
                    newCartDetail.setCart(cart);
                    newCartDetail.setProduct(product);
                    newCartDetail.setPrice(product.getPrice());
                    newCartDetail.setQuantity(quantity);
                    this.cartDetailService.handleSaveCartDetail(newCartDetail);

                    //Update Cart
                    int sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    this.cartService.handleSaveCart(cart);
                    session.setAttribute("sum", sum);
                }
            }
        }
    }



}
