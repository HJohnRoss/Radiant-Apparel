package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        // getting the total session did not work
        Double total = 0.0;
        for(Map<ProductDatabase, String> oneProduct : cart){
            for(Entry<ProductDatabase, String> oneKey : oneProduct.entrySet()){
                total += (oneKey.getKey().getPrice().getUnitAmount() * Integer.parseInt(oneKey.getValue()));
            }
        }
        model.addAttribute("total", total);

        model.addAttribute("cart", cart);
        return "cart.jsp";
    }
}
