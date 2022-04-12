package com.example.tema_repo_cookie.repository;

import com.example.tema_repo_cookie.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Iterable<Orders> getAllByCustomers_Id(Integer id);
}
