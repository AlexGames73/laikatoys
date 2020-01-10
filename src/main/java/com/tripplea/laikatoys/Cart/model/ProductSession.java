package com.tripplea.laikatoys.Cart.Model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductSession {

    private ArrayList<ProductCartDto> products;

    public ProductSession(){
        products = new ArrayList<ProductCartDto>();
    }

    public ArrayList<ProductCartDto> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductCartDto> products) {
        this.products = products;
    }
}
