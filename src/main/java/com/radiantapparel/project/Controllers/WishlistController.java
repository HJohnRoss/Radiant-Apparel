package com.radiantapparel.project.Controllers;

import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Models.User;
import com.radiantapparel.project.Services.ProductService;
import com.radiantapparel.project.Services.UserService;

@Controller
public class WishlistController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;

    @GetMapping("/wishlist/{id}")
    public String wishlist(@PathVariable("id")Long id, Model model, HttpSession session) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findUser(userId);
        
        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        model.addAttribute("user", user);
        model.addAttribute("wishlist", productService.userProducts(user));
        return "wishlist.jsp";
    }

    @PutMapping("/wishlist/remove/{id}")
    public String removeItem(@PathVariable("id")Long id, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        ProductDatabase product = productService.findProductById(id);
        User user = userService.findUser(userId);

        user.getProducts().remove(product);
        userService.updateUser(user);

        model.addAttribute("currencyFormat",NumberFormat.getCurrencyInstance());
        model.addAttribute("user", user);
        model.addAttribute("wishlist", productService.userProducts(user));
        return "redirect:/wishlist/" + userId;
    }
}
