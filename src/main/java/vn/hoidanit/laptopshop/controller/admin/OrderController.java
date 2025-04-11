package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.OrderDetailService;
import vn.hoidanit.laptopshop.service.OrderService;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public OrderController(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @RequestMapping(value = "/admin/order", method = RequestMethod.GET)
    public String getTableOrderPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 5);// limit = 2
        Page<Order> orderPageList = this.orderService.getAllOrders(pageable);
        List<Order> orderList = orderPageList.getContent();
        model.addAttribute("orderList", orderList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPageList.getTotalPages());
        return "admin/order/show";
    }

    // Before Delete Order
    @RequestMapping(value = "admin/order/delete/{id}", method = RequestMethod.GET)
    public String getDeleteOrderPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "admin/order/delete";
    }

    // After Delete Order
    @RequestMapping(value = "admin/order/delete", method = RequestMethod.POST)
    public String getTableOrderPageAfterDelete(Model model, @ModelAttribute("order") Order order) {
        Order orderDeleted = this.orderService.getOrderById(order.getId());
        this.orderDetailService.deleteOrderDetailByOrder(orderDeleted);
        this.orderService.deleteOrderById(orderDeleted.getId());
        return "redirect:/admin/order";
    }

    @RequestMapping(value = "/admin/order/detail/{id}", method = RequestMethod.GET)
    public String getDetailOrderPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);

        model.addAttribute("order", order);
        return "admin/order/detail";
    }

    // Before Update Order
    @RequestMapping(value = "/admin/order/update/{id}", method = RequestMethod.GET)
    public String getUpdateOrderPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "admin/order/update";
    }

    // After Update Order
    @RequestMapping(value = "/admin/order/update", method = RequestMethod.POST)
    public String getTableUserPageAfterUpdate(Model model, @ModelAttribute("order") Order order) {

        Order orderUpdated = this.orderService.getOrderById(order.getId());
        // User userUpdated = this.userService.getUserById(user.getId());
        if (orderUpdated != null) {
            orderUpdated.setStatus(order.getStatus());
            this.orderService.handleSaveOrder(orderUpdated);
        }
        return "redirect:/admin/order";
    }
}
