package com.example.tema_repo_cookie.dao;

import com.example.tema_repo_cookie.model.Orders;
import com.example.tema_repo_cookie.model.Products;
import com.example.tema_repo_cookie.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductsDAO implements DAO<Products> {
    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Optional<Products> get(Integer id) {
        return productsRepository.findById(id);
    }

    public List<Products> findAll() {
        return new ArrayList<>(productsRepository.findAll());
    }

    @Override
    public void create(Products products) {
        productsRepository.save(products);
    }

    @Override
    public void delete(Products products) {
        productsRepository.delete(products);
    }

    @Override
    public void update(Products products) {
        productsRepository.save(products);
    }
}
