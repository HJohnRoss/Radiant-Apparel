package com.radiantapparel.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Services.CategoryService;
import com.radiantapparel.project.Services.ProductService;
import com.radiantapparel.project.Services.TypeService;
import com.stripe.exception.StripeException;


@Controller
public class ShopController {

    @Autowired
    ProductService productService;

    @Autowired
    TypeService typeService;

    @Autowired
    CategoryService categoryService;
    
    @GetMapping("/shop")
    public String showShop(Model model) throws StripeException{
        model.addAttribute("allProducts", productService.allProducts());
        model.addAttribute("allTypes", typeService.allTypes());
        return "shop.jsp";
    }

    @PostMapping("/category/show/{id}")
    public String categoryProducts(@PathVariable("id")Long id, Model model) {
        model.addAttribute("allProducts", productService.allProducts());
        model.addAttribute("allTypes", typeService.allTypes());
        Category category = categoryService.oneCategory(id);
        model.addAttribute("categoryProducts", categoryService);
        return "redirect:/shop";
    }
}
