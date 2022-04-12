package com.example.tema_repo_cookie.dao;

import com.example.tema_repo_cookie.model.Customers;
import com.example.tema_repo_cookie.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CustomersDAO implements DAO<Customers> {
    @Autowired
    CustomersRepository customersRepository;

    @Override
    public Optional<Customers> get(Integer id) {
        return customersRepository.findById(id);
    }

    public List<Customers> findAllByCityAndUsernameAndCountry(String username, String city, String country) {
        return customersRepository.findAllByCityAndUsernameAndCountry(username, city, country);
    }

    public List<Customers> findAllByCityAndCountry(String city, String country) {
        return customersRepository.findAllByCityAndCountry(city, country);
    }

    public List<Customers> findAllByCityAndUsername(String username, String city) {
        return customersRepository.findAllByCityAndUsername(username, city);
    }

    public List<Customers> findAllByUsernameAndCountry(String username, String country) {
        return customersRepository.findAllByUsernameAndCountry(username, country);
    }

    public List<Customers> findAllByCountry(String country) {
        return customersRepository.findAllByCountry(country);
    }

    public List<Customers> findAllByCity(String city) {
        return customersRepository.findAllByCity(city);
    }

    public List<Customers> findAllByUsername(String username) {
        return customersRepository.findAllByUsername(username);
    }

    public List<Customers> findAll() {
        return new ArrayList<>(customersRepository.findAll());
    }

    @Override
    public void create(Customers customers) {
        if (!customersRepository.existsCustomersByUsername(customers.getUsername())) {
            customersRepository.save(customers);
        }
    }

    @Override
    public void delete(Customers customers) {
        customersRepository.delete(customers);
    }

    @Override
    public void update(Customers customers) {
        customersRepository.save(customers);
    }

    public boolean getCustomerByUsername(String username) {
        return customersRepository.existsCustomersByUsername(username);
    }

    public Optional<Customers> getCustomerByUsernameAndPassword(String username, String password) {
        return customersRepository.findCustomersByUsernameAndPassword(username, password);
    }
}
