package com.example.tema_bean.controller;

import com.example.tema_bean.DTO.OrderDTO;
import com.example.tema_bean.model.Order;
import com.example.tema_bean.service.IPaymentService;
import com.example.tema_bean.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {
    public static final Integer limit = 100;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private OrderService orderService;

    @Autowired
    private IPaymentService iPaymentService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping("/view/orders/{id}")
    public ModelAndView viewById(@PathVariable(name = "id") Long id) {
        Order c = this.orderService.getOrderById(id);

        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("order", c);

        return modelAndView;
    }

    @GetMapping("/view/orders")
    public ModelAndView viewAll() {
        List<Order> personList = this.orderService.getAllOrders();

        ModelAndView modelAndView = new ModelAndView("order_list");
        modelAndView.addObject("orderList", personList);

        return modelAndView;
    }

    @PostMapping("/view/orders")
    public ModelAndView addOrder(@RequestBody OrderDTO orderdto) {
        double price = orderdto.getPrice();

        if (price > limit) {
            if (activeProfile.equals("dev")) {
                iPaymentService = applicationContext.getBean("cashPaymentService_dev", IPaymentService.class);
            } else if (activeProfile.equals("prod")) {
                iPaymentService = applicationContext.getBean("cashPaymentService", IPaymentService.class);
            }
        } else{
            if (activeProfile.equals("dev")) {
                iPaymentService = applicationContext.getBean("cardPaymentService_dev", IPaymentService.class);
            } else if (activeProfile.equals("prod")) {
                iPaymentService = applicationContext.getBean("cardPaymentService", IPaymentService.class);
            }
        }

        Order order = orderService.createOrder(orderdto, iPaymentService.createPayment(orderdto.getPrice()));

        ModelAndView modelAndView = new ModelAndView("order_list");
        modelAndView.addObject("orderList", order);

        return modelAndView;
    }

}
