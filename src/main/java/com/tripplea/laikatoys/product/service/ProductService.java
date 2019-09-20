package com.tripplea.laikatoys.product.service;

import com.tripplea.laikatoys.product.model.Product;
import com.tripplea.laikatoys.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsSortById(int page, int countOnPage) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAll(pageable).getContent();
    }
}
