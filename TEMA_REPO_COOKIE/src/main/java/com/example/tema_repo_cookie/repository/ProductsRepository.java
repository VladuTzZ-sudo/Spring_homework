package com.example.tema_repo_cookie.repository;

import com.example.tema_repo_cookie.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    Iterable<Products> getAllByOrders_Id(Integer id);
}
