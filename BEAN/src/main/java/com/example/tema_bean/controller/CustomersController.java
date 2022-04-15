package com.example.tema_bean.controller;

import com.example.tema_bean.model.Customer;
import com.example.tema_bean.service.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("view")
public class CustomersController {
    @Autowired
    CustomersService customersService;
    @Value(value = "${com.dbcloud.msg}")
    String message_profile;

    @PostConstruct
    void postConstructor() {
        System.out.println(this.message_profile);
    }

    @GetMapping("/customers/{id}")
    public ModelAndView viewById(@PathVariable(name = "id") Long id) {
        Customer c = this.customersService.getCustomerById(id);

        ModelAndView modelAndView = new ModelAndView("customer");
        modelAndView.addObject("customer", c);

        return modelAndView;
    }

    @GetMapping("/customers")
    public ModelAndView filterCustomers(@RequestParam(name = "username", defaultValue = "") String username,
                                        @RequestParam(name = "city", defaultValue = "") String city,
                                        @RequestParam(name = "country", defaultValue = "") String country) {
        List<Customer> personList;

        personList = customersService.findAll(username, city, country);

        ModelAndView modelAndView = new ModelAndView("customer-list");
        modelAndView.addObject("customerList", personList);

        return modelAndView;
    }

    @PostMapping("/customers")
    public ModelAndView signUp(@RequestBody Customer customers) {
        ModelAndView modelAndView = new ModelAndView("customer");

        Customer customer = customersService.createCustomer(customers);

        modelAndView.addObject("customer", customer);

        return modelAndView;
    }
}
