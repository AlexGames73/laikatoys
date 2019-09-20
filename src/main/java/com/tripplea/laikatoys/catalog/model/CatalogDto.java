package com.tripplea.laikatoys.catalog.model;

import com.tripplea.laikatoys.product.model.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CatalogDto {
    @NotNull
    private int countOnPage;
    @NotNull
    private int page;
    @Size(max = 50)
    private String query;
    private String type;
    private List<Product> productList;

    public int getCountOnPage() {
        return countOnPage;
    }

    public void setCountOnPage(int countOnPage) {
        this.countOnPage = countOnPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
