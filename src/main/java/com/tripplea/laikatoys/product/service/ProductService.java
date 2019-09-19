package com.tripplea.laikatoys.product.service;

import com.tripplea.laikatoys.product.model.Product;
import com.tripplea.laikatoys.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }
}
