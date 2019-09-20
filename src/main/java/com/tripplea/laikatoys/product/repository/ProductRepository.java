package com.tripplea.laikatoys.product.repository;

import com.tripplea.laikatoys.product.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    List<Product> findAllByType(String type, Pageable pageable);
    List<Product> findAllByNameIgnoreCaseIsContaining(String s, Pageable pageable);
}
