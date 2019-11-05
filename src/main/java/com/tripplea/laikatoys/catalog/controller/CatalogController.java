package com.tripplea.laikatoys.catalog.controller;

import com.tripplea.laikatoys.catalog.service.CatalogService;
import com.tripplea.laikatoys.product.model.Product;
import com.tripplea.laikatoys.product.model.ProductCreateDto;
import com.tripplea.laikatoys.product.model.ProductDto;
import com.tripplea.laikatoys.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    public String getCatalog(Map<String, Object> model, @RequestParam(value = "p", required = false) Integer page) {
        Page<Product> resp = catalogService.getProducts(page == null ? 0 : page, 10);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : resp.getContent()) {
            productDtos.add(new ProductDto(product.getName(), product.getType(), product.getDescription(), product.getPrice(), product.getUris()));
        }
        model.put("productDtos", productDtos);
        model.put("countPages", resp.getTotalPages());
        return "catalog/catalog";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createProduct(@ModelAttribute ProductCreateDto productCreateDto) {
        catalogService.createProduct(productCreateDto);
        return "redirect:/catalog";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createProductPage(Map<String, Object> model) {
        model.put("productCreateDto", new ProductCreateDto());
        return "admin/createproduct";
    }
}
