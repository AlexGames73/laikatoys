package com.tripplea.laikatoys.Cart.Controller;

import com.tripplea.laikatoys.Cart.Model.CountCartInfo;
import com.tripplea.laikatoys.Cart.Model.ProductCartDto;
import com.tripplea.laikatoys.Cart.Model.ProductSession;
import com.tripplea.laikatoys.product.model.ProductDto;
import com.tripplea.laikatoys.user.model.User;
import com.tripplea.laikatoys.user.service.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

@Controller
@SessionAttributes("cart")
@RequestMapping("/cart")
public class CartRestController {

    @ModelAttribute("cart")
    public ProductSession addStuffToRequestScope() {
        return new ProductSession();
    }

    @PostMapping("/updateCart")
    @ResponseBody
    public ResponseEntity<String> updateCarts(@RequestBody CountCartInfo countCartInfo, @ModelAttribute("cart") ProductSession cartSession, HttpSession session){
        for (ProductCartDto pcd : cartSession.getProducts()) {
            if (pcd.getProductDto().getId() == countCartInfo.id){
                pcd.setCount(countCartInfo.count);
                break;
            }
        }
        session.setAttribute("cart", cartSession);
        return ResponseEntity.ok(cartSession.toString());
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addProduct(@RequestBody ProductCartDto productCartDto, @ModelAttribute("cart") ProductSession cartSession, HttpSession session) {
        ArrayList<ProductCartDto> buf = cartSession.getProducts();
        boolean isContains = false;
        for (ProductCartDto pcd : cartSession.getProducts()) {
            if (productCartDto.getProductDto().getId() == pcd.getProductDto().getId()){
                isContains = true;
                if (productCartDto.getCount() > 0)
                    pcd = productCartDto;
            }
        }
        if (!isContains){
            if (productCartDto.getCount() > 0) {
                buf.add(productCartDto);
            }
        }
        cartSession.setProducts(buf);
        session.setAttribute("cart", cartSession);
        return ResponseEntity.ok(cartSession.toString());
    }

    @PostMapping("/delCart")
    @ResponseBody
    public ResponseEntity<String> delCart(@RequestBody CountCartInfo countCartInfo, @ModelAttribute("cart") ProductSession cartSession, HttpSession session){
        for (ProductCartDto pcd : cartSession.getProducts()) {
            if (pcd.getProductDto().getId() == countCartInfo.id){
                cartSession.getProducts().remove(pcd);
            }
        }
        session.setAttribute("cart", cartSession);
        return ResponseEntity.ok(cartSession.toString());
    }

    @GetMapping("/show")
    public String showCarts(Model model, HttpSession session){
        List<ProductCartDto> buf = new ArrayList<ProductCartDto>();
        if (session.getAttribute("cart") != null) {
            buf = ((ProductSession) session.getAttribute("cart")).getProducts();
        }
        model.addAttribute("products", buf);
        return "cart/showCards";
    }
}
