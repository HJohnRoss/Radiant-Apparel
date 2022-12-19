package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }
        return "product.jsp";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, @RequestParam("quantity") String quantity, HttpSession session) throws StripeException{
        // getting product from our database
        ProductDatabase product = productService.findProductById(productId);

        Map<ProductDatabase, String> cartObject = new HashMap<ProductDatabase, String>();
        cartObject.put(product, quantity);


        ArrayList<Map<ProductDatabase, String>> cart = (ArrayList) session.getAttribute("cart");

        boolean inCart = false;
        for(Map<ProductDatabase, String> oneProduct : cart){
            for(Entry<ProductDatabase, String> oneKey : oneProduct.entrySet()){
                if(oneKey.getKey().getId().equals(product.getId())){
                    oneProduct = cartObject;
                    inCart = true;
                }
            }
        }
        if(inCart == false){
            cart.add(cartObject);
        }
        session.setAttribute("cart", cart);
        
        return "redirect:/product/show/{productId}";
    }
}