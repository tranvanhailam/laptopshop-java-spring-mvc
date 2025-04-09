package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public void deleteOrderDetailByOrder(Order order) {
        List<OrderDetail> orderDetailList = this.orderDetailRepository.findByOrder(order);
        for (OrderDetail orderDetail : orderDetailList) {
            this.orderDetailRepository.deleteById(orderDetail.getId());
        }
    }

    // public OrderDetail handleSaveOrderDetail(OrderDetail orderDetail) {
    // return this.orderDetailRepository.save(orderDetail);
    // }

}
