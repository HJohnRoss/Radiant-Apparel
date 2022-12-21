package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.radiantapparel.project.Models.ProductDatabase;
import com.stripe.exception.StripeException;

@Controller
public class SuccessController {
    @GetMapping("/success")
    public String showDashboard(Model model, HttpSession session) throws StripeException{
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

        return "success.jsp";
    }

    @GetMapping("/success/dashboard")
    public String dashboard(HttpSession session){
        session.setAttribute("cart", null);
        return "redirect:/";
    }

    @GetMapping("/success/about")
    public String about(HttpSession session){
        session.setAttribute("cart", null);
        return "redirect:/about";
    }

    @GetMapping("/success/shop")
    public String shop(HttpSession session){
        session.setAttribute("cart", null);
        return "redirect:/shop";
    }
    @GetMapping("/success/wishlist/{id}")
    public String wishlist(HttpSession session){
        session.setAttribute("cart", null);
        return "redirect:/wishlist/{id}";
    }
    @GetMapping("/success/admin")
    public String admin(HttpSession session){
        session.setAttribute("cart", null);
        return "redirect:/admin";
    }
}
