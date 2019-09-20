package com.tripplea.laikatoys.catalog.service;

import com.tripplea.laikatoys.product.model.Product;
import com.tripplea.laikatoys.product.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    private final ProductRepository productRepository;

    public CatalogService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(int page, int countOnPage) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAll(pageable).getContent();
    }

    public List<Product> getProductsByType(int page, int countOnPage, String type) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAllByType(type, pageable);
    }

    public List<Product> getProductsByNameQuery(int page, int countOnPage, String query) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAllByNameIgnoreCaseIsContaining(query, pageable);
    }
}
