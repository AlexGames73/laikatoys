package com.tripplea.laikatoys.Cart.Model;


import com.tripplea.laikatoys.product.model.ProductDto;

public class ProductCartDto {
    private ProductDto productDto;
    private Integer count;

    public ProductCartDto() {
    }

    public ProductCartDto(ProductDto productDto, Integer count) {
        this.productDto = productDto;
        this.count = count;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
