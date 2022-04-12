package com.example.tema_repo_cookie.Service;

import com.example.tema_repo_cookie.dao.OrdersDAO;
import com.example.tema_repo_cookie.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired
    OrdersDAO ordersDAO;

    public List<Orders> getAllOrders() {
        return ordersDAO.findAll();
    }

    public Orders getOrderById(Integer id) {
        Optional<Orders> orders = ordersDAO.get(id);
        return orders.orElse(null);
    }

    public void deleteOrderById(Integer id) {
        ordersDAO.delete(getOrderById(id));
    }

    public Orders saveOrder(Orders orders) {
        ordersDAO.update(orders);
        return orders;
    }
}
