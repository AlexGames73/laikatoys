package com.tripplea.laikatoys.catalog.service;

import com.tripplea.laikatoys.API.DBFile.model.DBFile;
import com.tripplea.laikatoys.API.DBFile.service.DBFileStorageService;
import com.tripplea.laikatoys.product.model.Product;
import com.tripplea.laikatoys.product.model.ProductCreateDto;
import com.tripplea.laikatoys.product.model.ProductDto;
import com.tripplea.laikatoys.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CatalogService {
    private final ProductRepository productRepository;
    private final DBFileStorageService dbFileStorageService;

    public CatalogService(ProductRepository productRepository, DBFileStorageService dbFileStorageService) {
        this.productRepository = productRepository;
        this.dbFileStorageService = dbFileStorageService;
    }

    public Page<Product> getProducts(int page, int countOnPage) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAll(pageable);
    }

    public List<Product> getProductsByType(int page, int countOnPage, String type) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAllByType(type, pageable);
    }

    public List<Product> getProductsByNameQuery(int page, int countOnPage, String query) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAllByNameIgnoreCaseIsContaining(query, pageable);
    }

    public void createProduct(ProductCreateDto productCreateDto) {
        Product product = new Product(productCreateDto);
        Set<DBFile> dbFiles = new HashSet<>();
        for (MultipartFile file : productCreateDto.getFiles()) {
            DBFile dbFile = dbFileStorageService.storeFile(file);
            dbFiles.add(dbFile);
        }
        product.setImages(dbFiles);
        productRepository.save(product);
    }
}
