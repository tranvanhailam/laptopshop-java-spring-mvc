package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;

@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailService(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    public CartDetail handleSaveCartDetail(CartDetail cartDetail) {
        return this.cartDetailRepository.save(cartDetail);
    }

    public boolean checkExistsProductInCart(Cart cart, Product product) {
        return this.cartDetailRepository.existsByCartAndProduct(cart, product);
    }

    public CartDetail handleUpdateQuantityOfProductInCart(Cart cart, Product product) {
        CartDetail cartDetail = this.cartDetailRepository.findFirstByCartAndProduct(cart, product);
        cartDetail.setQuantity(cartDetail.getQuantity() + 1);
        return this.cartDetailRepository.save(cartDetail);
    }

    public CartDetail getCartDetailByCartAndProduct(Cart cart, Product product) {
        return this.cartDetailRepository.findFirstByCartAndProduct(cart, product);
    }

}
