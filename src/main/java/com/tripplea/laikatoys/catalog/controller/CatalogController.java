package com.tripplea.laikatoys.catalog.controller;

import com.tripplea.laikatoys.catalog.service.CatalogService;
import com.tripplea.laikatoys.product.model.Product;
import com.tripplea.laikatoys.product.model.ProductDto;
import com.tripplea.laikatoys.user.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public String getCatalog(Map<String, Object> model, @AuthenticationPrincipal User user) {
        List<Product> products = catalogService.getProducts(1, 2);
        List<ProductDto> productDtos = new LinkedList<>();
        for (Product product : products) {
            productDtos.add(product.toDto());
        }
        model.put("currentUser", user);
        model.put("catalogDto", productDtos);
        return "catalog/catalog";
    }

    @PostMapping("/create")
    public String createProduct(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("description") String description,
            @RequestParam("price") Integer price,
            @RequestParam("files") MultipartFile[] files
    ) {
        catalogService.createProduct(name, type, description, price, files);
        return "redirect:/catalog";
    }

    @GetMapping("/create")
    public String createProductPage(Map<String, Object> model) {
        return "admin/createproduct";
    }
}
