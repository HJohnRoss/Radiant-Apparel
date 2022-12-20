package com.radiantapparel.project.Controllers;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.radiantapparel.project.Models.LoginUser;
import com.radiantapparel.project.Models.ProductDatabase;
import com.radiantapparel.project.Models.User;
import com.radiantapparel.project.Services.UserService;

@Controller
public class LoginController {
    
    @Autowired
    UserService userService;

	@GetMapping("/login")
	public String index(Model model, HttpSession session) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());

        if(session.getAttribute("cart") == null){
            ArrayList<Map<ProductDatabase, String>> newCart = new ArrayList<>();
            session.setAttribute("cart", newCart);
            model.addAttribute("cart", session.getAttribute("cart"));
        }
		return "login.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {
		User user = userService.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/loguser")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result,
			Model model, HttpSession session) {
		User user = userService.login(newLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "login.jsp";
		}
		session.setAttribute("userId", user.getId());
		session.setAttribute("userName", user.getFirstName());
		
		return "redirect:/";
	}
}
