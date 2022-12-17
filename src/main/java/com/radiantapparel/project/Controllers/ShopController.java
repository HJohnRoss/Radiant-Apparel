package com.radiantapparel.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.radiantapparel.project.Services.ProductService;
import com.stripe.exception.StripeException;

@Controller
public class ShopController {

    @Autowired
    ProductService productService;
    
    @GetMapping
    public String showShop(Model model) throws StripeException{
        model.addAttribute("allProducts", productService.allProducts());
        return "shop.jsp";
    }
}
