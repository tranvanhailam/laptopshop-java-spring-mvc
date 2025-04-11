package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.order.ReceiverInfoDTO;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    public OrderService(OrderRepository orderRepository,
            CartService cartService, OrderDetailRepository orderDetailRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.orderDetailRepository = orderDetailRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        return this.orderRepository.findById(id);
    }

    public Order handleSaveOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public void deleteOrderById(long id) {
        this.orderRepository.deleteById(id);
    }

    public List<Order> getOrderByUser(User user) {
        return this.orderRepository.findByUser(user);
    }

    public void handlePlaceOrder(User user, HttpSession session, ReceiverInfoDTO receiverInfoDTO) {

        Cart cart = this.cartService.getCartByUser(user);

        if (cart != null) {
            List<CartDetail> cartDetailList = cart.getCartDetails();

            if (cartDetailList != null) {

                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverInfoDTO.getFullName());
                order.setReceiverAddress(receiverInfoDTO.getAddress());
                order.setReceiverPhone(receiverInfoDTO.getPhoneNumber());
                order.setStatus("PENDING");
                double totalPrice = 0;

                for (CartDetail cartDetail : cartDetailList) {
                    totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
                }

                order.setTotalPrice(totalPrice);
                order = this.orderRepository.save(order);

                // Create OrderDetail
                for (CartDetail cartDetail : cartDetailList) {
                    totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cartDetail.getProduct());
                    orderDetail.setPrice(cartDetail.getPrice());
                    orderDetail.setQuantity(cartDetail.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }

                // Delete CartDetail, Cart
                for (CartDetail cartDetail : cartDetailList) {
                    this.cartDetailRepository.deleteById(cartDetail.getId());
                }

                this.cartRepository.deleteById(cart.getId());

                // Update Session
                session.setAttribute("sum", 0);

            }
        }
    }
}
