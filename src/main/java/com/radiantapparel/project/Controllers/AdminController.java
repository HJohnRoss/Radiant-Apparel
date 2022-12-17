package com.radiantapparel.project.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.radiantapparel.project.Models.PriceDatabase;
import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Services.PriceService;
import com.radiantapparel.project.Services.ProductService;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;
    
    @Autowired
    PriceService priceService;

    @GetMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("product", new ProductDatabase());
        model.addAttribute("price", new PriceDatabase());

        // =================================== LEFT OFF HERE STARTING TO MAKE PRICES FOR PRODUCTS =============================
        model.addAttribute("allProducts", productService.allProducts());
        return "admin.jsp";
    }

    @PostMapping("/product/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductDatabase product, BindingResult result,
            Model model, @RequestParam("name") String name, @RequestParam("images") String image)
            throws StripeException {

        if(result.hasErrors()){
            model.addAttribute("product", product);
            return "admin.jsp";
        }

        
        // create project inside of stripe
        HashMap<String, Object> params = new HashMap<>();
        // creating a array to store the images in
        ArrayList<String> images = new ArrayList<>();
        images.add(image);
        params.put("name", name);
        params.put("images", images);
        
        Product stripeProduct = Product.create(params);
        
        // create project in our database
        productService.createProduct(product, stripeProduct);


        return "redirect:/admin";
    }
}
