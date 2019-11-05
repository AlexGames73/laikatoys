package com.tripplea.laikatoys.Cart.repository;

import com.tripplea.laikatoys.Cart.model.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartRepository extends PagingAndSortingRepository<Cart, Integer> {
}
