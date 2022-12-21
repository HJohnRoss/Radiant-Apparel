package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.radiantapparel.project.Models.ProductDatabase;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Controller
public class PaymentController {
    
    @GetMapping("/checkout")
    public String showCheckout(Model model, HttpSession session) {
        ArrayList<Map<ProductDatabase, String>> cart = (ArrayList<Map<ProductDatabase, String>>) session.getAttribute("cart");
        Double total = 0.0;
        for(Map<ProductDatabase, String> oneProduct : cart){
            for(Entry<ProductDatabase, String> oneKey : oneProduct.entrySet()){
                total += (oneKey.getKey().getPrice().getUnitAmount() * Integer.parseInt(oneKey.getValue()));
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cart);
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        return "payment.jsp";
    }
}