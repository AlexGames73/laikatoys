package com.tripplea.laikatoys.product.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String type;
    private String description;
    private Integer price;

    @ElementCollection
    @CollectionTable(name="images_uris", joinColumns=@JoinColumn(name="productdto_id"))
    @Column(name="image_uri")
    private List<String> imagesUris;

    public ProductDto() {
    }

    public ProductDto(String name, String type, String description, Integer price, List<String> imagesUris) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.imagesUris = imagesUris;
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

    public List<String> getImagesUris() {
        return imagesUris;
    }

    public void setImagesUris(List<String> imagesUris) {
        this.imagesUris = imagesUris;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return name.equals(that.name) &&
                type.equals(that.type) &&
                description.equals(that.description) &&
                price.equals(that.price) &&
                imagesUris.equals(that.imagesUris);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, description, price, imagesUris);
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\": \"" + name + '"' +
                ", \"type\": \"" + type + '"' +
                ", \"description\": \"" + description + '"' +
                ", \"price\": " + price +
                ", \"imagesUris\": [\"" + String.join("\", \"", imagesUris) + "\"]" +
                '}';
    }
}
