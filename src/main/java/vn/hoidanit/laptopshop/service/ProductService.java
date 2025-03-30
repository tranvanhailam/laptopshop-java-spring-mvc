package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final UserService userService;
    private final CartDetailService cartDetailService;

    public ProductService(ProductRepository productRepository, CartService cartService, UserService userService,
            CartDetailService cartDetailService) {
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.cartDetailService = cartDetailService;
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

    public void handleAddProductToCart(String email, long id) {
        User user = this.userService.getFirstUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartService.getCartByUser(user);
            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(1);
                cart = this.cartService.handleSaveCart(newCart);
            }

            Product product = this.productRepository.findById(id);
            if (product != null) { 

                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cart);
                cartDetail.setProduct(product);
                cartDetail.setPrice(product.getPrice());
                cartDetail.setQuantity(1);
                this.cartDetailService.handleSaveCartDetail(cartDetail);
            }

        }
    }

}
