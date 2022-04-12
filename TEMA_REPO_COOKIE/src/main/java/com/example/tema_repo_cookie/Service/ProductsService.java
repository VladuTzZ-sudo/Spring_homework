package com.example.tema_repo_cookie.Service;

import com.example.tema_repo_cookie.dao.OrdersDAO;
import com.example.tema_repo_cookie.dao.ProductsDAO;
import com.example.tema_repo_cookie.model.Orders;
import com.example.tema_repo_cookie.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    ProductsDAO productsDAO;

    public List<Products> getAllProducts() {
        return productsDAO.findAll();
    }

    public Products getProductById(Integer id) {
        Optional<Products> products = productsDAO.get(id);
        return products.orElse(null);
    }

    public void deleteProductById(Integer id) {
        productsDAO.delete(getProductById(id));
    }

    public Products saveProduct(Products products) {
        productsDAO.update(products);
        return products;
    }
}
