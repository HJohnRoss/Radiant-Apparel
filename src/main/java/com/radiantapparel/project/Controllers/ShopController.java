package com.radiantapparel.project.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;

@Controller
public class ShopController {
    
    @GetMapping
    public String showShop(Model model) throws StripeException{
        Map<String, Object> params = new HashMap<>();
        ProductCollection products = Product.list(params);
        model.addAttribute("allProducts", products);
        return "shop.jsp";
    }
}
