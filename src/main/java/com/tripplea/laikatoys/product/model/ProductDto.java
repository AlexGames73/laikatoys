package com.tripplea.laikatoys.product.model;

import java.util.List;

public class ProductDto {
    private String name;
    private String type;
    private String description;
    private Integer price;
    private List<String> ImagesUri;

    public ProductDto() {
    }

    public ProductDto(String name, String type, String description, Integer price, List<String> imagesUri) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        ImagesUri = imagesUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getImagesUri() {
        return ImagesUri;
    }

    public void setImagesUri(List<String> imagesUri) {
        ImagesUri = imagesUri;
    }
}
