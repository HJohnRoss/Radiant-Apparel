package com.radiantapparel.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.radiantapparel.project.Models.Product;
import com.radiantapparel.project.Services.ProductService;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String showAdmin(Model model){
        model.addAttribute("product", new Product());
        return "admin.jsp";
    }
}
