package com.example.tema_bean.service;

import com.example.tema_bean.DTO.OrderDTO;
import com.example.tema_bean.model.Customer;
import com.example.tema_bean.model.Order;
import com.example.tema_bean.model.Payment;
import com.example.tema_bean.model.PaymentMethod;
import com.example.tema_bean.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository ordersRepository;
    @Autowired
    CustomersService customersService;

    public List<Order> getAllOrders() {
        return new ArrayList<>(ordersRepository.findAll());
    }

    public Order getOrderById(Long id) {
        Optional<Order> orders = ordersRepository.findById(id);
        return orders.orElse(null);
    }

    public Order createOrder(OrderDTO orderDTO, Payment payment) {
        Customer customer = orderDTO.getCustomer();
        Order order = ordersRepository.save(Order.builder().payment(payment).price(orderDTO.getPrice()).customer(customer).build());
        if (customer != null) {
            if (customer.getOrderList() == null) {
                customer.setOrderList(new ArrayList<>());
            }
            customer.getOrderList().add(order);

            customersService.saveCustomer(customer);
        }
        return order;
    }
}
