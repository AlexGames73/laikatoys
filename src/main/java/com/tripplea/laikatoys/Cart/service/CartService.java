package com.tripplea.laikatoys.Cart.service;

import com.tripplea.laikatoys.Cart.model.Cart;
import com.tripplea.laikatoys.Cart.repository.CartRepository;
import com.tripplea.laikatoys.Cart.repository.ProductDtoRepository;
import com.tripplea.laikatoys.product.model.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductDtoRepository productDtoRepository;

    public CartService(CartRepository cartRepository, ProductDtoRepository productDtoRepository) {
        this.cartRepository = cartRepository;
        this.productDtoRepository = productDtoRepository;
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public void saveProductDto(ProductDto productDto) {
        productDtoRepository.save(productDto);
    }
}
