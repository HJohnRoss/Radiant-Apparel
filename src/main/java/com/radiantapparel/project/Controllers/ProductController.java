package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Models.Review;
import com.radiantapparel.project.Models.User;
import com.radiantapparel.project.Services.ProductService;
import com.radiantapparel.project.Services.ReviewService;
import com.radiantapparel.project.Services.UserService;


@Controller
public class ProductController {
    
    @Autowired
    ProductService productService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @GetMapping("/product/show/{id}")
    public String showProduct(@PathVariable("id")Long id, Model model, Review review, HttpSession session) {
        boolean loggedIn = false;

        if(session.getAttribute("userId") != null) {
            model.addAttribute("userId", session.getAttribute("userId"));
            Long userId = (Long) session.getAttribute("userId");
            model.addAttribute("wishlist", productService.userProducts(userService.findUser(userId)));
            loggedIn = true;
        }
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        model.addAttribute("productReviews", reviewService.productReviews(id));
        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }
        model.addAttribute("loggedIn", loggedIn);
        return "product.jsp";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, @RequestParam("quantity") String quantity, HttpSession session) {
        // getting product from our database
        ProductDatabase product = productService.findProductById(productId);
        
        Map<ProductDatabase, String> cartObject = new HashMap<ProductDatabase, String>();
        cartObject.put(product, quantity);
        
        
        ArrayList<Map<ProductDatabase, String>> cart = (ArrayList) session.getAttribute("cart");
        
        boolean inCart = false;
        for(Map<ProductDatabase, String> oneProduct : cart){
            for(Entry<ProductDatabase, String> oneKey : oneProduct.entrySet()){
                if(oneKey.getKey().getId().equals(product.getId())){
                    oneProduct.replace(oneKey.getKey(), quantity);
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

    @PostMapping("/product/review/{productId}")
    public String postReview(@Valid @ModelAttribute("review")Review review, BindingResult result, @PathVariable("productId")Long id, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if(userId == null) {
            return "redirect:/product/show/" + id;
        }
        if(result.hasErrors()) {
            model.addAttribute("product", productService.findProductById(id));
            model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
            model.addAttribute("productReviews", reviewService.productReviews(id));
            if(session.getAttribute("cart") == null){
                ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
                session.setAttribute("cart", newCart);
                model.addAttribute("cart", session.getAttribute("cart"));
            }
            return "product.jsp";
        }
        Review newReview = reviewService.createReview(review);
        productService.addReview(id, newReview);
        return "redirect:/product/show/" + id;
    }

    @PostMapping("/wishlist/add/{id}")
    public String addItem(@PathVariable("id")Long id, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findUser(userId);
        if(userId == null) {
            return "redirect:product/show/" + id;
        }
        if(!user.getProducts().contains(productService.findProductById(id))) {
            productService.addProduct(id, user);
            userService.updateUser(user);
        }
        return "redirect:/product/show/" + id;
    }
}