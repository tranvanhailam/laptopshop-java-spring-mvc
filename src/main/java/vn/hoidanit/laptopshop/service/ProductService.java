package vn.hoidanit.laptopshop.service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.order.ReceiverInfoDTO;
import vn.hoidanit.laptopshop.domain.dto.product.ProductCriteriaDTO;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.specification.ProductSpecifications;

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

    public Page<Product> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Page<Product> getAllProductsWithSpecification(ProductCriteriaDTO productCriteriaDTO, Pageable pageable) {
        Specification<Product> combinedSpecification = Specification.where(null);

        if (productCriteriaDTO.getFactory() != null && productCriteriaDTO.getTarget() == null) {
            return this.productRepository.findAll(pageable);
        }

        if (productCriteriaDTO.getTarget() != null && productCriteriaDTO.getTarget().isPresent()) {
            Specification<Product> currentSpecification = ProductSpecifications
                    .targetListEqual(productCriteriaDTO.getTarget().get());
            combinedSpecification = combinedSpecification.and(currentSpecification);
        }

        if (productCriteriaDTO.getPrice() != null && productCriteriaDTO.getPrice().isPresent()) {
            Specification<Product> currentSpecification = buildPriceCombinedSpecification(
                    productCriteriaDTO.getPrice().get(), pageable);
            combinedSpecification = combinedSpecification.and(currentSpecification);
        }

        return this.productRepository.findAll(combinedSpecification, pageable);
    }

    // public Page<Product> getAllProductsWithSpecification(double minPrice,
    // Pageable pageable) {
    // return
    // this.productRepository.findAll(ProductSpecifications.priceGreaterThanOrEqualTo(minPrice),
    // pageable);
    // }

    // public Page<Product> getAllProductsWithSpecification(double maxPrice,
    // Pageable pageable) {
    // return
    // this.productRepository.findAll(ProductSpecifications.priceLessThanOrEqualTo(maxPrice),
    // pageable);
    // }

    // public Page<Product> getAllProductsWithSpecification(String factory, Pageable
    // pageable) {
    // return
    // this.productRepository.findAll(ProductSpecifications.factoryEqual(factory),
    // pageable);
    // }

    // public Page<Product> getAllProductsWithSpecification(List<String>
    // factoryList, Pageable pageable) {
    // return
    // this.productRepository.findAll(ProductSpecifications.factoryListEqual(factoryList),
    // pageable);
    // }

    // public Page<Product> getAllProductsWithSpecification(String price, Pageable
    // page) {
    // // eg: price 10-toi-15-trieu
    // if (price.equals("10000000-15000000")) {
    // double minPrice = 10000000;
    // double maxPrice = 15000000;
    // return this.productRepository.findAll(
    // ProductSpecifications.priceGreaterThanOrEqualAndLessThanOrEqualTo(minPrice,
    // maxPrice),
    // page);

    // } else if (price.equals("15000000-20000000")) {
    // double minPrice = 15000000;
    // double maxPrice = 20000000;
    // return this.productRepository.findAll(ProductSpecifications
    // .priceGreaterThanOrEqualAndLessThanOrEqualTo(minPrice, maxPrice),
    // page);
    // } else if (price.equals("20000000-30000000")) {
    // double minPrice = 20000000;
    // double maxPrice = 30000000;
    // return this.productRepository.findAll(ProductSpecifications
    // .priceGreaterThanOrEqualAndLessThanOrEqualTo(minPrice, maxPrice),
    // page);
    // } else
    // return this.productRepository.findAll(page);
    // }

    public Specification<Product> buildPriceCombinedSpecification(List<String> priceList,
            Pageable page) {
        Specification<Product> combinedSpecification = Specification.where(null);
        // int count = 0;

        for (String price : priceList) {
            double minPrice = 0;
            double maxPrice = 0;

            switch (price) {
                case "under-10000000":
                    minPrice = 0;
                    maxPrice = 10000000;
                    // count++;
                    break;
                case "10000000-15000000":
                    minPrice = 10000000;
                    maxPrice = 15000000;
                    // count++;
                    break;
                case "15000000-20000000":
                    minPrice = 15000000;
                    maxPrice = 20000000;
                    // count++;
                    break;
                case "over-20000000":
                    minPrice = 20000000;
                    maxPrice = 200000000;
                    // count++;
                    break;

                default:
                    break;
            }

            if (minPrice != 0 || maxPrice != 0) {
                Specification<Product> rangSpecification = ProductSpecifications
                        .priceMultipleGreaterThanOrEqualAndLessThanOrEqualTo(minPrice, maxPrice);
                combinedSpecification = combinedSpecification.or(rangSpecification);
            }
        }

        return combinedSpecification;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public void deleteProductByID(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long id, int quantity, HttpSession session) {
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
                    // new CartDetail
                    CartDetail newCartDetail = new CartDetail();
                    newCartDetail.setCart(cart);
                    newCartDetail.setProduct(product);
                    newCartDetail.setPrice(product.getPrice());
                    newCartDetail.setQuantity(quantity);
                    this.cartDetailService.handleSaveCartDetail(newCartDetail);

                    // Update Cart
                    int sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    this.cartService.handleSaveCart(cart);
                    session.setAttribute("sum", sum);
                }
            }
        }
    }

    public Specification<Product> queryByName(String name) {
        return (root, query, builder) -> builder.like(root.get(Product_.NAME), "%" + name + "%");
    }

}
