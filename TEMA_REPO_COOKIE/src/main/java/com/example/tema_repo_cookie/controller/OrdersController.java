package com.example.tema_repo_cookie.controller;

import com.example.tema_repo_cookie.Service.OrdersService;
import com.example.tema_repo_cookie.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @GetMapping("/view/orders/{id}")
    public ModelAndView viewById(@PathVariable(name = "id") int id) {
        Orders c = this.ordersService.getOrderById(id);

        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("order", c);

        return modelAndView;
    }

    @GetMapping("/view/orders")
//    @ResponseBody
    public ModelAndView viewAll() {
        List<Orders> personList = this.ordersService.getAllOrders();

        ModelAndView modelAndView = new ModelAndView("order_list");
        modelAndView.addObject("orderList", personList);

        return modelAndView;
    }
}

