package com.radiantapparel.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.radiantapparel.project.Services.ProductService;


@Controller
public class ProductController {
    
    @Autowired
    ProductService productService;

    @GetMapping("/product/show/{id}")
    public String showProduct(@PathVariable("id")Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        return "product.jsp";
    }
}
