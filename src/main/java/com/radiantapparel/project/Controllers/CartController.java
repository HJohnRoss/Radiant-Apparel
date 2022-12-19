package com.radiantapparel.project.Controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.model.checkout.SessionCollection;

@Controller
public class CartController {
    
    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) throws StripeException{


        model.addAttribute("cart", session.getAttribute("cart"));
        return "cart.jsp";
    }
}
