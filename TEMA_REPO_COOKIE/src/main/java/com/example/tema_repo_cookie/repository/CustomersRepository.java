package com.example.tema_repo_cookie.repository;

import com.example.tema_repo_cookie.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {
    List<Customers> findAllByCityAndUsernameAndCountry(String username, String city, String country);

    List<Customers> findAllByUsernameAndCountry(String username, String country);

    List<Customers> findAllByCountry(String country);

    List<Customers> findAllByCityAndCountry(String city, String country);

    List<Customers> findAllByCityAndUsername(String username, String city);

    List<Customers> findAllByUsername(String username);

    List<Customers> findAllByCity(String city);

    Boolean existsCustomersByUsername(String username);

    Optional<Customers> findCustomersByUsernameAndPassword(String username, String password);
}
