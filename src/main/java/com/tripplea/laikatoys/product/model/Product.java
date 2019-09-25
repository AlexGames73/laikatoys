package com.tripplea.laikatoys.product.model;

import com.tripplea.laikatoys.API.DBFile.model.DBFile;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String type;
    private String description;
    private Integer price;

    @ElementCollection(targetClass = DBFile.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private Set<DBFile> images;

    public Product() {
    }

    public Product(String name, String type, String description, Integer price, Set<DBFile> images) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<DBFile> getImages() {
        return images;
    }

    public void setImages(Set<DBFile> images) {
        this.images = images;
    }

    public ProductDto toDto() {
        List<String> uris = new LinkedList<>();
        for (DBFile file : images) {
            uris.add("/downloadFile/" + file.getId());
        }
        return new ProductDto(name, type, description, price, uris);
    }
}
