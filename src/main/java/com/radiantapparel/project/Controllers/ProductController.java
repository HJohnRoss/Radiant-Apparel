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
            model.addAttribute("cart", session.getAttribute("cart"));
        }
        return "product.jsp";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable("productId") String productId, @RequestParam("quantity") String quantity, HttpSession session) throws StripeException{
        // getting product from our database

        Map<String, String> cartObject = new HashMap<String, String>();
        cartObject.put(productId, quantity);


        ArrayList<Map<String, String>> cart = (ArrayList) session.getAttribute("cart");

        boolean inCart = false;
        for(Map<String, String> oneProduct : cart){
            if(oneProduct.containsKey((String) productId)){
                oneProduct.replace(productId, quantity);
                inCart = true;
            }
        }
        if(inCart == false){
            cart.add(cartObject);
        }
        session.setAttribute("cart", cart);
        
        return "redirect:/product/show/{productId}";
    }
}
