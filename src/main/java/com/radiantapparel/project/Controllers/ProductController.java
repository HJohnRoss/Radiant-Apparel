package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Services.ProductService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;


@Controller
public class ProductController {
    
    @Autowired
    ProductService productService;

    @GetMapping("/product/show/{id}")
    public String showProduct(@PathVariable("id")Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        return "product.jsp";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, @RequestParam("quantity") String quantity) throws StripeException{
        // getting product from our database
        ProductDatabase product = productService.findProductById(productId);

        List<Object> lineItems = new ArrayList<>();
        Map<String, Object> lineItem1 = new HashMap<>();
        lineItem1.put("price", product.getPrices().get(0).getStripePriceId());
        lineItem1.put("quantity", quantity);
        lineItems.add(lineItem1);
        Map<String, Object> params = new HashMap<>();
        params.put(
        "success_url",
        "https://success"
        );
        params.put(
        "cancel_url",
        "https://cancel"
        );
        params.put("line_items", lineItems);
        params.put("mode", "payment");

        Session.create(params);
        return "redirect:/product/show/{productId}";
    }
}
