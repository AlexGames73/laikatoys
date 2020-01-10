package com.tripplea.laikatoys.catalog.controller;

import com.tripplea.laikatoys.Cart.Model.ProductCartDto;
import com.tripplea.laikatoys.Cart.Model.ProductSession;
import com.tripplea.laikatoys.catalog.Model.FiltersModel;
import com.tripplea.laikatoys.catalog.service.CatalogService;
import com.tripplea.laikatoys.product.model.Product;
import com.tripplea.laikatoys.product.model.ProductCreateDto;
import com.tripplea.laikatoys.product.model.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/catalog")
@SessionAttributes(value = "carts")
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public String getCatalog(Map<String, Object> model, HttpSession session,
                             @RequestParam(value = "p", required = false) Integer page,
                             @RequestParam(value = "startPrice", required = false) Integer startPrice,
                             @RequestParam(value = "endPrice", required = false) Integer endPrice,
                             @RequestParam(value = "isTransport", required = false) boolean isTransport,
                             @RequestParam(value = "isMebel", required = false) boolean isMebel,
                             @RequestParam(value = "isKind", required = false) boolean isKind,
                             @RequestParam(value = "isOther", required = false) boolean isOther) {
        HashSet<Integer> haveIdCarts = new HashSet<Integer>();
        FiltersModel filtersModel = new FiltersModel();
        filtersModel = catalogService.setSettingFilter(startPrice, endPrice, isTransport, isMebel, isKind, isOther);
        if (session.getAttribute("cart") != null) {
            List<ProductCartDto> buf = ((ProductSession) session.getAttribute("cart")).getProducts();
            for (int i = 0; i < buf.size(); i++) {
                if (!haveIdCarts.contains(buf.get(i).getProductDto().getId())) {
                    haveIdCarts.add(buf.get(i).getProductDto().getId());
                }
            }
        }
        Page<Product> resp = catalogService.getProducts(page == null ? 0 : page, 10);
        List<Product> products;
        List<String> typesStr= new ArrayList<String>();
        if (filtersModel.isTransport()) typesStr.add("Транспорт");
        if (filtersModel.isMebel()) typesStr.add("Мебель");
        if (filtersModel.isKind()) typesStr.add("Для детей");
        if (filtersModel.isOther()) typesStr.add("Другое");
        products = catalogService.getProductsByTypes(page == null ? 0 : page, 10, typesStr);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() <= filtersModel.getEndPrice() && product.getPrice() >= filtersModel.getStartPrice())
                productDtos.add(new ProductDto(product.getId(), product.getName(), product.getType(), product.getDescription(), product.getPrice(), product.getUris()));
        }
        model.put("productDtos", productDtos);
        model.put("countPages", resp.getTotalPages());
        model.put("haveIdCarts", haveIdCarts);
        model.put("filter", filtersModel);
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
