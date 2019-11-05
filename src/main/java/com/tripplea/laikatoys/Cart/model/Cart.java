package com.tripplea.laikatoys.Cart.model;

import com.tripplea.laikatoys.product.model.ProductDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_count_mapping",
            joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "productdto_id")
    @Column(name = "count")
    private Set<ProductCartDto> productCarts;
    private String promoCode;

    public Cart() {
    }

    public Cart(Set<ProductCartDto> productCarts, String promoCode) {
        this.productCarts = productCarts;
        this.promoCode = promoCode;
    }

    public Set<ProductCartDto> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(Set<ProductCartDto> productCarts) {
        this.productCarts = productCarts;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (ProductCartDto entry : productCarts) {
            productDtos.add(entry.getProductDto());
            counts.add(entry.getCount());
        }
        return "{" +
                "\"productNames\": [" +
                    productDtos.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                "]," +
                "\"productCounts\": [" +
                    counts.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                "]" +
                "}";
    }
}
