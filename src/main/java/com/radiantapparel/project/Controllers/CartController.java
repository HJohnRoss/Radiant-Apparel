package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Services.ProductService;
import com.stripe.exception.StripeException;

@Controller
public class CartController {
    
    @Autowired
    ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) throws StripeException{
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());

        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }

        ArrayList<Map<ProductDatabase, String>> cart = (ArrayList<Map<ProductDatabase, String>>) session.getAttribute("cart");

        model.addAttribute("cart", cart);
        return "cart.jsp";
    }

    @PostMapping("/cart/update/{productId}")
    public String updateQuantity(@PathVariable("productId") Long productId, @RequestParam("quantity") String quantity, HttpSession session){
        ProductDatabase product = productService.findProductById(productId);

        ArrayList<Map<ProductDatabase, String>> cart = (ArrayList) session.getAttribute("cart");

        for(Map<ProductDatabase, String> oneProduct : cart){
            for(Entry<ProductDatabase, String> oneKey : oneProduct.entrySet()){
                if(oneKey.getKey().getId().equals(product.getId())){
                    oneProduct.replace(oneKey.getKey(), quantity);
                }
            }
        }
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @PostMapping("/cart/delete/{productId}")
    public String deleteCart(@PathVariable("productId") Long productId, HttpSession session){
        ProductDatabase product = productService.findProductById(productId);

        ArrayList<Map<ProductDatabase, String>> cart = (ArrayList) session.getAttribute("cart");

        for(Map<ProductDatabase, String> oneProduct : cart){
            for(Entry<ProductDatabase, String> oneKey : oneProduct.entrySet()){
                if(oneKey.getKey().getId().equals(product.getId())){
                    cart.remove(cart.indexOf(oneProduct));
                    session.setAttribute("cart", cart);
                    return "redirect:/cart";
                }
            }
        }
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }
}
