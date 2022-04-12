package com.example.tema_repo_cookie.dao;

import com.example.tema_repo_cookie.model.Customers;
import com.example.tema_repo_cookie.model.Orders;
import com.example.tema_repo_cookie.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrdersDAO implements DAO<Orders> {
    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public Optional<Orders> get(Integer id) {
        return ordersRepository.findById(id);
    }

    public List<Orders> findAll() {
        return new ArrayList<>(ordersRepository.findAll());
    }

    @Override
    public void create(Orders orders) {
        ordersRepository.save(orders);
    }

    @Override
    public void delete(Orders orders) {
        ordersRepository.delete(orders);
    }

    @Override
    public void update(Orders orders) {
        ordersRepository.save(orders);
    }
}