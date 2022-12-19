package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.radiantapparel.project.Models.ProductDatabase;
import com.stripe.exception.StripeException;

@Controller
public class CartController {
    
    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) throws StripeException{
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());

        ArrayList<Map<ProductDatabase, String>> cart = (ArrayList<Map<ProductDatabase, String>>) session.getAttribute("cart");

        ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
        for(Map<ProductDatabase, String> product : cart){
            newCart.add(product);
        }
        model.addAttribute("cart", newCart);
        return "cart.jsp";
    }
}
