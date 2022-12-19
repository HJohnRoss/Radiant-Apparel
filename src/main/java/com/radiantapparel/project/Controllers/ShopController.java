package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("categoryProducts", productService.allProducts());
        model.addAttribute("allTypes", typeService.allTypes());
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        return "shop.jsp";
    }

    @GetMapping("/category/show/{id}")
    public String categoryProducts(@PathVariable("id")Long id, Model model) {
        model.addAttribute("allTypes", typeService.allTypes());
        Category category = categoryService.oneCategory(id);
        List<ProductDatabase> categoryProducts = productService.findAllByCategory(category);
        model.addAttribute("categoryProducts", categoryProducts);
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        return "shop.jsp";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("name")String name, Model model) {
        model.addAttribute("categoryProducts", productService.findByName(name));
        model.addAttribute("allTypes", typeService.allTypes());
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        return "shop.jsp";
    }

}
