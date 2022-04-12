package com.example.tema_repo_cookie.controller;

import com.example.tema_repo_cookie.Service.ProductsService;
import com.example.tema_repo_cookie.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @GetMapping("/view/products/{id}")
    public ModelAndView viewById(@PathVariable(name = "id") int id) {
        Products c = productsService.getProductById(id);

        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("product", c);

        return modelAndView;
    }

    @GetMapping("/view/products")
//    @ResponseBody
    public ModelAndView viewAll() {
        List<Products> personList = this.productsService.getAllProducts();

        ModelAndView modelAndView = new ModelAndView("product-list");
        modelAndView.addObject("products", personList);

        return modelAndView;
    }
}
