package com.tripplea.laikatoys.catalog.service;

import com.tripplea.laikatoys.API.DBFile.model.DBFile;
import com.tripplea.laikatoys.API.DBFile.service.DBFileStorageService;
import com.tripplea.laikatoys.catalog.Model.FiltersModel;
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
import java.util.logging.Filter;

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

    public List<Product> getProductsByTypes(int page, int countOnPage, List<String> types) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAllByTypeIn(types, pageable);
    }

    public List<Product> getProductsByType(int page, int countOnPage, String type) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAllByType(type, pageable);
    }

    public List<Product> getProductsByNameQuery(int page, int countOnPage, String query) {
        Pageable pageable = PageRequest.of(page, countOnPage, Sort.by("id"));
        return productRepository.findAllByNameIgnoreCaseIsContaining(query, pageable);
    }

    public FiltersModel setSettingFilter(Integer startPrice, Integer endPrice, boolean isTransport, boolean isMebel, boolean isKind, boolean isOther){
        FiltersModel filtersModel = new FiltersModel();
        if (startPrice != null)
            filtersModel.setStartPrice(startPrice);
        if (endPrice != null)
            filtersModel.setEndPrice(endPrice);
        if (startPrice != null) {
            filtersModel.setTransport(isTransport);
            filtersModel.setMebel(isMebel);
            filtersModel.setKind(isKind);
            filtersModel.setOther(isOther);
        }
        return  filtersModel;
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
