package com.radiantapparel.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Models.ProductDatabase;
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

    @GetMapping("/category/show/{id}")
    public String categoryProducts(@PathVariable("id")Long id, Model model) {
        model.addAttribute("allProducts", productService.allProducts());
        model.addAttribute("allTypes", typeService.allTypes());
        Category category = categoryService.oneCategory(id);
        List<ProductDatabase> categoryProducts = productService.findAllByCategory(category);
        model.addAttribute("categoryProducts", categoryProducts);
        return "shop.jsp";
    }

}
