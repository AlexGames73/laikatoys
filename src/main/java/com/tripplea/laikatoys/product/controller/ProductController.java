package com.tripplea.laikatoys.product.controller;

import com.tripplea.laikatoys.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String hello(Map<String, Object> model) {
        model.put("name", "Alex");

        return "";
    }
}
