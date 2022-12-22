package com.radiantapparel.project.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.radiantapparel.project.Models.Category;
import com.radiantapparel.project.Models.PriceDatabase;
import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Models.Review;
import com.radiantapparel.project.Models.Type;
import com.radiantapparel.project.Models.User;
import com.radiantapparel.project.Services.CategoryService;
import com.radiantapparel.project.Services.PriceService;
import com.radiantapparel.project.Services.ProductService;
import com.radiantapparel.project.Services.ReviewService;
import com.radiantapparel.project.Services.TypeService;
import com.radiantapparel.project.Services.UserService;
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

    @Autowired
    ReviewService reveiwService;

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String showAdmin(Model model, HttpSession session) {
        model.addAttribute("product", new ProductDatabase());
        model.addAttribute("price", new PriceDatabase());
        model.addAttribute("category", new Category());
        model.addAttribute("type", new Type());


        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }

        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("allProducts", productService.allProducts());
        model.addAttribute("allTypes", typeService.allTypes());
        return "admin.jsp";
    }

    // CREATE PRODUCT
    @PostMapping("/product/create")
    public String createProduct(@Valid @ModelAttribute("product") ProductDatabase product, BindingResult result,
            Model model, @RequestParam("name") String name, @RequestParam("images") String image, HttpSession session)
            throws StripeException {

        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("price", new PriceDatabase());
            model.addAttribute("category", new Category());
            model.addAttribute("type", new Type());
            model.addAttribute("allProducts", productService.allProducts());
            model.addAttribute("allTypes", typeService.allTypes());
            model.addAttribute("userId", session.getAttribute("userId"));
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
            Model model, @RequestParam("unitAmount") String unitAmount, HttpSession session) throws StripeException {

        if(result.hasErrors()){
            model.addAttribute("price", price);
            model.addAttribute("product", new ProductDatabase());
            model.addAttribute("category", new Category());
            model.addAttribute("type", new Type());
            model.addAttribute("allProducts", productService.allProducts());
            model.addAttribute("allTypes", typeService.allTypes());
            model.addAttribute("userId", session.getAttribute("userId"));
            return "admin.jsp";
        }


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(unitAmount);
        for(int i = 0; i < stringBuilder.length(); i++){
            if(stringBuilder.charAt(i) == '.'){
                stringBuilder.deleteCharAt(i);
            }
        }
        Integer.parseInt(stringBuilder.toString());

        PriceDatabase newPrice = priceService.createPrice(price);

        Map<String, Object> params = new HashMap<>();
        params.put("unit_amount", Integer.parseInt(stringBuilder.toString()));
        params.put("currency", "usd");
        params.put("product", newPrice.getProduct().getStripeProductId());

        Price stripePrice = Price.create(params);

        newPrice.setStripePriceId(stripePrice.getId());
        priceService.createPrice(newPrice);
        
        return "redirect:/admin";
    }

    // CREATE TYPE
    @PostMapping("/type/create")
    public String createType(@Valid @ModelAttribute("type") Type type, BindingResult result,
    Model model, HttpSession session){

        if(result.hasErrors()){
            model.addAttribute("type", type);
            model.addAttribute("category", new Category());
            model.addAttribute("price", new Price());
            model.addAttribute("product", new ProductDatabase());
            model.addAttribute("allProducts", productService.allProducts());
            model.addAttribute("allTypes", typeService.allTypes());
            model.addAttribute("userId", session.getAttribute("userId"));
            return "admin.jsp";
        }

        typeService.createType(type);
        return "redirect:/admin";
    }

    // CREATE CATEGORY
    @PostMapping("/category/create")
    public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result,
    Model model, HttpSession session){

        if(result.hasErrors()){
            model.addAttribute("category", category);
            model.addAttribute("price", new Price());
            model.addAttribute("product", new ProductDatabase());
            model.addAttribute("type", new Type());
            model.addAttribute("allProducts", productService.allProducts());
            model.addAttribute("allTypes", typeService.allTypes());
            model.addAttribute("userId", session.getAttribute("userId"));
            return "admin.jsp";
        }

        categoryService.createCategory(category);
        return "redirect:/admin";
    }

    // ONE TYPE
    @GetMapping("/type/{id}")
    public String showType(@PathVariable("id") Long id, Model model, HttpSession session){

        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }

        model.addAttribute("oneType", typeService.oneType((Long) id));
        model.addAttribute("userId", session.getAttribute("userId"));
        return "adminType.jsp";
    }

    // ONE CATEGORY
    @GetMapping("/category/{id}")
    public String showCategory(@PathVariable("id") Long id, Model model, HttpSession session){
        
        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }

        model.addAttribute("oneCategory", categoryService.oneCategory(id));
        model.addAttribute("someProducts", productService.findByCategoriesNotContains(categoryService.oneCategory(id)));
        model.addAttribute("userId", session.getAttribute("userId"));
        return "adminCategory.jsp";
    }

    // ADDING A PRODUCT TO A CATEGORY
    @PutMapping("/product/add/{id}")
    public String addProduct(@PathVariable("id") Long categoryId, @RequestParam("productId") Long productId){

        categoryService.addProduct(categoryId, productId);
        return "redirect:/category/{id}";
    }

    // ========================================== DELETE ==========================================
    // delete product
    @GetMapping("/products/delete")
    public String showDeleteProducts(Model model, HttpSession session){

        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }

        model.addAttribute("allProducts", productService.allProducts());
        model.addAttribute("userId", session.getAttribute("userId"));
        return "adminDeleteProducts.jsp";
    }

    @DeleteMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId, @RequestParam("stripeProductId") String stripeProductId) throws StripeException{

        
        // getting stripe product object
        Product StripeProduct = Product.retrieve(stripeProductId);

        // setting productive to false
        Map<String, Object> params = new HashMap<>();
        params.put("active", false);

        StripeProduct.update(params);
        
        // setting categories = null
        ProductDatabase product = productService.oneProduct(productId);
        product.setCategories(null);

        // deleting reviews for a product
        for(Review oneReview : productService.productReviews(productId)){
            reveiwService.deleteReview(oneReview);
        }

        // deleting wishlists for a product
        ProductDatabase oneProduct = productService.findProductById(productId);
        for(User oneUser : oneProduct.getUsers()){
            userService.deleteUser(oneUser);
        }

        // deleting all the prices for that product
        priceService.deletePrice(productService.productPrices(productId));

        // delete product from our database
        productService.deleteProduct(productId);

        return "redirect:/products/delete";
    }

    // delete types
    @GetMapping("/type/delete")
    public String showDeleteTypes(Model model, HttpSession session){
        
        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }

        model.addAttribute("allTypes", typeService.allTypes());
        model.addAttribute("userId", session.getAttribute("userId"));
        return "adminDeleteTypes.jsp";
    }

    @DeleteMapping("/type/delete/{id}")
    public String deleteType(@PathVariable("id") Long typeId){

        for(Category oneCategory : typeService.typeCategories(typeId)){
            categoryService.deleteCategory(oneCategory);
        }

        typeService.deleteType(typeId);
        return "redirect:/type/delete";
    }

    // delete Categories
    @GetMapping("/category/delete")
    public String showDeleteCategories(Model model, HttpSession session){

        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }

        model.addAttribute("allCategories", categoryService.allCategories());
        return "adminDeleteCategories.jsp";
    }

    @DeleteMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId){

        Category oneCategory = categoryService.oneCategory(categoryId);
        oneCategory.setProducts(null);

        categoryService.deleteCategory(categoryId);
        return "redirect:/category/delete";
    }
}
