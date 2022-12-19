package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
    public String showProduct(@PathVariable("id")Long id, Model model, HttpSession session) {
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        if(session.getAttribute("cart") == null){
            ArrayList<ProductDatabase> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
        }
        return "product.jsp";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, HttpSession session) throws StripeException{
        // getting product from our database
        ProductDatabase product = productService.findProductById(productId);
        

        // List<Object> lineItems = new ArrayList<>();
        // Map<String, Object> lineItem1 = new HashMap<>();
        // lineItem1.put("price", product.getPrices().get(0).getStripePriceId());
        // lineItem1.put("quantity", quantity);
        // lineItems.add(lineItem1);
        // Map<String, Object> params = new HashMap<>();
        // params.put(
        // "success_url",
        // "https://success"
        // );
        // params.put(
        // "cancel_url",
        // "https://cancel"
        // );
        // params.put("line_items", lineItems);
        // params.put("mode", "payment");

        // Session stripeSession = Session.create(params);

        ArrayList<ProductDatabase> cart = (ArrayList) session.getAttribute("cart");

        cart.add(product);
        session.setAttribute("cart", cart);
        
        return "redirect:/product/show/{productId}";
    }
}
