package com.tripplea.laikatoys.Cart.controller;

import com.tripplea.laikatoys.Cart.model.Cart;
import com.tripplea.laikatoys.Cart.model.ProductCartDto;
import com.tripplea.laikatoys.Cart.service.CartService;
import com.tripplea.laikatoys.product.model.ProductDto;
import com.tripplea.laikatoys.user.model.User;
import com.tripplea.laikatoys.user.service.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@SessionAttributes("cart")
@RequestMapping("/cart")
public class CartRestController {

    private final UserServices userServices;
    private final CartService cartService;

    public CartRestController(UserServices userServices, CartService cartService) {
        this.userServices = userServices;
        this.cartService = cartService;
    }

    @ModelAttribute("cart")
    public Cart addStuffToRequestScope() {
        return new Cart(new HashMap<>(), "");
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addProduct(@RequestBody ProductCartDto productCartDto, @ModelAttribute("cart") Cart cartSession, @AuthenticationPrincipal User authUser, HttpSession session) {
        if (authUser == null) {
            Set<ProductCartDto> buf = cartSession.getProductCarts();
            if (buf.contains(productCartDto)) {
                productCartDto.setCount();
                buf.remove(productCartDto);
                buf.add()
            }
            else
                buf.put(productCartDto.getProductDto(), productCartDto.getCount() == null ? 1 : productCartDto.getCount());
            cartSession.setProductCarts(buf);
            session.setAttribute("cart", cartSession);
            return ResponseEntity.ok(cartSession.toString());
        } else {
            Cart cartUser = authUser.getCart() == null ? new Cart(new HashMap<>(), "") : authUser.getCart();
            Set<ProductCartDto> buf = cartUser.getProductCarts();
            cartService.saveProductDto(productCartDto.getProductDto());
            if (buf.containsKey(productCartDto.getProductDto()))
                buf.replace(productCartDto.getProductDto(), buf.get(productCartDto.getProductDto()) + 1);
            else
                buf.put(productCartDto.getProductDto(), productCartDto.getCount() == null ? 1 : productCartDto.getCount());
            cartUser.setProductCarts(buf);
            cartService.save(cartUser);
            authUser.setCart(cartUser);
            userServices.save(authUser);
            return ResponseEntity.ok(cartUser.toString());
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> getCart(@ModelAttribute("cart") Cart cartSession, @AuthenticationPrincipal User authUser) {
        if (authUser == null)
            return ResponseEntity.ok(cartSession.toString());
        return ResponseEntity.ok(authUser.getCart().toString());
    }
}
