package com.example.tema_bean.repository;

import com.example.tema_bean.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByCityAndUsernameAndCountry(String username, String city, String country);

    List<Customer> findAllByUsernameAndCountry(String username, String country);

    List<Customer> findAllByCountry(String country);

    List<Customer> findAllByCityAndCountry(String city, String country);

    List<Customer> findAllByCityAndUsername(String username, String city);

    List<Customer> findAllByUsername(String username);

    List<Customer> findAllByCity(String city);

    Boolean existsCustomersByUsername(String username);
}
