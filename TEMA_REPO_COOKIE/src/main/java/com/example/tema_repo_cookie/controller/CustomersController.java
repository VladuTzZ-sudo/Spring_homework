package com.example.tema_repo_cookie.controller;

import com.example.tema_repo_cookie.Service.CustomersService;
import com.example.tema_repo_cookie.dto.CustomersLoginDTO;
import com.example.tema_repo_cookie.model.Customers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("view")
public class CustomersController {
    @Autowired
    CustomersService customersService;

    /*@PostMapping()
    void createCustomer(@RequestBody Customers customers) {
        customersService.saveCustomer(customers);
    }*/

    /*
    @GetMapping("/customers")
    public ModelAndView viewAll() {
        List<Customers> customers = customersService.findAll();

        ModelAndView modelAndView = new ModelAndView("customers-list");
        modelAndView.addObject("customerList", customers);

        return modelAndView;
    }*/

    @GetMapping("/customers/{id}")
    public ModelAndView viewById(@PathVariable(name = "id") int id) {
        Customers c = this.customersService.getCustomerById(id);

        ModelAndView modelAndView = new ModelAndView("customer");
        modelAndView.addObject("customer", c);

        return modelAndView;
    }

    @GetMapping("/customers")
    public ModelAndView filterCustomers(@RequestParam(name = "username", defaultValue = "") String username,
                                        @RequestParam(name = "city", defaultValue = "") String city,
                                        @RequestParam(name = "country", defaultValue = "") String country) {
        List<Customers> personList;

        personList = customersService.findAll(username, city, country);

        ModelAndView modelAndView = new ModelAndView("customer-list");
        modelAndView.addObject("customerList", personList);

        return modelAndView;
    }

    @PutMapping("/customers/signup")
    public ModelAndView signUp(@RequestBody Customers customers) {
        ModelAndView modelAndView = new ModelAndView("after-register-page");

        Customers customer = customersService.signUp(customers);
        if (customer != null) {
            modelAndView.addObject("customer", customer);
        } else {
            String message = "Exista deja un utilizator cu acest username !";
            modelAndView.addObject("message", message);
        }

        return modelAndView;
    }

    @GetMapping("/customers/{id}/homepage")
    public ModelAndView viewHomePage(@PathVariable(name = "id") int id,
                                     @CookieValue(name = "username") String username) {
        ModelAndView modelAndView = new ModelAndView("customer-homepage");

        if (customersService.compareCookies(id, username)) {
            modelAndView.addObject("customer", customersService.getCustomerById(id));
        }
        return modelAndView;
    }

    @PostMapping("/customers/login")
    public ResponseEntity<ModelAndView> login(@RequestBody CustomersLoginDTO customers) {
        ModelAndView modelAndView = new ModelAndView("customer");
        return customersService.login(customers, modelAndView);
    }
}
