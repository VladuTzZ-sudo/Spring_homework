package com.example.tema_repo_cookie.Service;

import com.example.tema_repo_cookie.dao.CustomersDAO;
import com.example.tema_repo_cookie.dto.CustomersLoginDTO;
import com.example.tema_repo_cookie.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Service
public class CustomersService {
    @Autowired
    CustomersDAO customersDAO;

    public List<Customers> getAllCustomers() {
        return customersDAO.findAll();
    }

    public List<Customers> findAll(String username, String city, String country) {
        if (Objects.equals(username, "")) {
            if (Objects.equals(city, "")) {
                if (Objects.equals(country, "")) {
                    return findAll();
                } else {
                    return customersDAO.findAllByCountry(country);
                }
            } else {
                if (Objects.equals(country, "")) {
                    return customersDAO.findAllByCity(city);
                } else {
                    return customersDAO.findAllByCityAndCountry(city, country);
                }
            }
        } else {
            if (Objects.equals(city, "")) {
                if (Objects.equals(country, "")) {
                    return customersDAO.findAllByUsername(username);
                } else {
                    return customersDAO.findAllByUsernameAndCountry(username, country);
                }
            } else {
                if (Objects.equals(country, "")) {
                    return customersDAO.findAllByCityAndUsername(city, username);
                } else {
                    return customersDAO.findAllByCityAndUsernameAndCountry(city, username, country);
                }
            }
        }
    }

    public List<Customers> findAll() {
        return new ArrayList<>(customersDAO.findAll());
    }

    public Customers getCustomerById(Integer id) {
        Optional<Customers> customer = customersDAO.get(id);
        return customer.orElse(null);
    }

    public Customers checkCredentials(String username, String password) {
        Optional<Customers> customer = customersDAO.getCustomerByUsernameAndPassword(username, password);
        return customer.orElse(null);
    }

    public void deleteCustomerById(Integer id) {
        customersDAO.delete(getCustomerById(id));
    }

    public Customers saveCustomer(Customers customers) {
        customersDAO.update(customers);
        return customers;
    }

    public Customers createCustomer(Customers customers) {
        customersDAO.create(customers);
        return customers;
    }

    public boolean existsByUsername(String username) {
        return customersDAO.getCustomerByUsername(username);
    }

    public ResponseEntity<ModelAndView> login(@RequestBody CustomersLoginDTO customers, ModelAndView modelAndView) {
        String password = Arrays.toString(DigestUtils.md5Digest(
                customers.getPassword().getBytes())).toUpperCase();
        Customers customer_logged_in = checkCredentials(customers.getUsername(), password);
        if (customer_logged_in != null) {
            modelAndView.addObject("customer", customer_logged_in);

            String cookie = String.valueOf(customer_logged_in.getUsername().hashCode());
            ResponseCookie springCookie = ResponseCookie.from("username", cookie)
                    .httpOnly(true)
                    .secure(false)
                    .domain("localhost")
                    .path("/")
                    .maxAge(120)
                    .build();

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, springCookie.toString()).body(modelAndView);
        } else {
            String message = "Credentiale gresite !";
            modelAndView.addObject("message", message);

            return ResponseEntity.notFound().build();
        }
    }

    public boolean compareCookies(Integer id, String username) {
        Customers c = getCustomerById(id);
        return String.valueOf(c.getUsername().hashCode()).compareTo(username) == 0;
    }

    public Customers signUp(Customers customers) {
        if (!existsByUsername(customers.getUsername()) &&
                getCustomerById(customers.getId()) == null) {
            String password = Arrays.toString(DigestUtils.md5Digest(
                    customers.getPassword().getBytes())).toUpperCase();
            customers.setPassword(password);
            createCustomer(customers);
            return customers;
        }
        return null;
    }
}
