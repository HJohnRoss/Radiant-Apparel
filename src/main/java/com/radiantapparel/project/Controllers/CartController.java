package com.radiantapparel.project.Controllers;

import java.util.ArrayList;

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
        ArrayList<ProductDatabase> cart = (ArrayList<ProductDatabase>) session.getAttribute("cart");

        ArrayList<ProductDatabase> newCart = new ArrayList<>();
        for(ProductDatabase product : cart){
            newCart.add(product);
        }
        model.addAttribute("cart", newCart);
        return "cart.jsp";
    }
}
