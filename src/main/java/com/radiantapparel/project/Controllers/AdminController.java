package com.radiantapparel.project.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Models.PriceDatabase;
import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Models.Type;
import com.radiantapparel.project.Services.CategoryService;
import com.radiantapparel.project.Services.PriceService;
import com.radiantapparel.project.Services.ProductService;
import com.radiantapparel.project.Services.TypeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    TypeService typeService;

    @GetMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("product", new ProductDatabase());
        model.addAttribute("price", new PriceDatabase());
        model.addAttribute("category", new Category());
        model.addAttribute("type", new Type());


        model.addAttribute("allProducts", productService.allProducts());
        model.addAttribute("allTypes", typeService.allTypes());
        return "admin.jsp";
    }

    // CREATE PRODUCT
    @PostMapping("/product/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductDatabase product, BindingResult result,
            Model model, @RequestParam("name") String name, @RequestParam("images") String image)
            throws StripeException {

        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("price", new PriceDatabase());
            model.addAttribute("category", new Category());
            model.addAttribute("type", new Type());
            model.addAttribute("allProducts", productService.allProducts());
            model.addAttribute("allTypes", typeService.allTypes());
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


    // CREATE PRICE
    @PostMapping("/price/create")
    public String createPrice(@Valid @ModelAttribute("price") PriceDatabase price, BindingResult result,
            Model model, @RequestParam("unitAmount") String unitAmount) throws StripeException {

        if(result.hasErrors()){
            model.addAttribute("price", price);
            model.addAttribute("product", new ProductDatabase());
            model.addAttribute("category", new Category());
            model.addAttribute("type", new Type());
            model.addAttribute("allProducts", productService.allProducts());
            model.addAttribute("allTypes", typeService.allTypes());
            return "admin.jsp";
        }
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(unitAmount.toString());
        for(int i = 0; i < stringBuilder.length(); i++){
            if(stringBuilder.charAt(i) == '.'){
                stringBuilder.deleteCharAt(i);
            }
        }
        int StripeUnitAmount = Integer.parseInt(stringBuilder.toString());

        PriceDatabase newPrice = priceService.createPrice(price);

        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", StripeUnitAmount);
        params.put("currency", "usd");
        params.put("product", newPrice.getProduct().getStripeProductId());

        Price.create(params);
        
        return "redirect:/admin";
    }

    // CREATE TYPE
    @PostMapping("/type/create")
    public String createType(@Valid @ModelAttribute("type") Type type, BindingResult result,
    Model model){

        if(result.hasErrors()){
            model.addAttribute("type", type);
            model.addAttribute("category", new Category());
            model.addAttribute("price", new Price());
            model.addAttribute("product", new ProductDatabase());
            model.addAttribute("allProducts", productService.allProducts());
            model.addAttribute("allTypes", typeService.allTypes());
        }

        typeService.createType(type);
        return "redirect:/admin";
    }

    // CREATE CATEGORY
    @PostMapping("/category/create")
    public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
    Model model){

        if(result.hasErrors()){
            model.addAttribute("category", category);
            model.addAttribute("price", new Price());
            model.addAttribute("product", new ProductDatabase());
            model.addAttribute("type", new Type());
            model.addAttribute("allProducts", productService.allProducts());
            model.addAttribute("allTypes", typeService.allTypes());
            return "admin.jsp";
        }

        categoryService.createCategory(category);
        return "redirect:/admin";
    }
}
