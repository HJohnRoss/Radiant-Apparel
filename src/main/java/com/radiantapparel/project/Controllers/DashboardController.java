package com.radiantapparel.project.Controllers;

import java.util.ArrayList;
import java.util.Map;
import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Services.ProductService;
import com.stripe.exception.StripeException;


@Controller
public class DashboardController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String showDashboard(Model model, HttpSession session) throws StripeException{
        
        model.addAttribute("allProducts", productService.allProducts());
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());

        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }

        return "dashboard.jsp";
    }

}
