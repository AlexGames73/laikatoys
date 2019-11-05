package com.tripplea.laikatoys.Cart.repository;

import com.tripplea.laikatoys.product.model.ProductDto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductDtoRepository extends PagingAndSortingRepository<ProductDto, Integer> {
}
