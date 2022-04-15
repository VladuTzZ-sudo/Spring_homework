package com.example.tema_bean.service;

import com.example.tema_bean.model.Customer;
import com.example.tema_bean.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomersService {
    @Autowired
    CustomerRepository customersRepository;

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    public List<Customer> findAll(String username, String city, String country) {
        if (Objects.equals(username, "")) {
            if (Objects.equals(city, "")) {
                if (Objects.equals(country, "")) {
                    return findAll();
                } else {
                    return customersRepository.findAllByCountry(country);
                }
            } else {
                if (Objects.equals(country, "")) {
                    return customersRepository.findAllByCity(city);
                } else {
                    return customersRepository.findAllByCityAndCountry(city, country);
                }
            }
        } else {
            if (Objects.equals(city, "")) {
                if (Objects.equals(country, "")) {
                    return customersRepository.findAllByUsername(username);
                } else {
                    return customersRepository.findAllByUsernameAndCountry(username, country);
                }
            } else {
                if (Objects.equals(country, "")) {
                    return customersRepository.findAllByCityAndUsername(city, username);
                } else {
                    return customersRepository.findAllByCityAndUsernameAndCountry(city, username, country);
                }
            }
        }
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customersRepository.findAll());
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customersRepository.findById(id);
        return customer.orElse(null);
    }

    public void deleteCustomerById(Long id) {
        customersRepository.delete(getCustomerById(id));
    }

    public Customer saveCustomer(Customer customers) {
        customersRepository.save(customers);
        return customers;
    }

    public Customer createCustomer(Customer customers) {
        if (!customersRepository.existsCustomersByUsername(customers.getUsername())) {
            customersRepository.save(customers);
        }
        return customers;
    }

    public boolean existsByUsername(String username) {
        return customersRepository.existsCustomersByUsername(username);
    }
}
