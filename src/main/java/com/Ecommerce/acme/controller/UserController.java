package com.Ecommerce.acme.controller;

import com.Ecommerce.acme.service.OrderService;
import com.Ecommerce.acme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService os;

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {

		if (error != null)
			model.addAttribute("error", "Your username or password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping("/default")
	public String defaultAfterLogin(Authentication authentication) {
		String currentRole = userService.findByUsername(authentication.getName()).getRole().getText();

		if ((currentRole == "ADMIN")) {
			return "redirect:/admin/orders";
		}
			return "redirect:/home";
	}

	@GetMapping({"/home"})
	public String welcome(Model model ) {
		return "index";
	}

	@GetMapping({"/profil"})
	public String profil(Model model, Authentication authentication) {
		os.getDataProfil(authentication, model);

		return "profil";
	}

}