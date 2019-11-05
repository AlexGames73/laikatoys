package com.tripplea.laikatoys.product.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductCreateDto {
    private String name;
    private String type;
    private String description;
    private Integer price;
    private MultipartFile[] files;

    public ProductCreateDto() {
    }

    public ProductCreateDto(String name, String type, String description, Integer price, MultipartFile[] files) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.files = files;
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

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
