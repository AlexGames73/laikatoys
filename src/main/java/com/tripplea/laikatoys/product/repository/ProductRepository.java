package com.tripplea.laikatoys.product.repository;

import com.tripplea.laikatoys.product.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
