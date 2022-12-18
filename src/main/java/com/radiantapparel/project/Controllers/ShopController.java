package com.radiantapparel.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.radiantapparel.project.Services.ProductService;
import com.radiantapparel.project.Services.TypeService;
import com.stripe.exception.StripeException;


@Controller
public class ShopController {

    @Autowired
    ProductService productService;

    @Autowired
    TypeService typeService;
    
    @GetMapping("/shop")
    public String showShop(Model model) throws StripeException{
        model.addAttribute("allProducts", productService.allProducts());
        model.addAttribute("allTypes", typeService.allTypes());
        return "shop.jsp";
    }

    @PostMapping("/category/{id}")
    public String categoryProducts(Model model) {
        return "redirect:/shop";
    }
}
