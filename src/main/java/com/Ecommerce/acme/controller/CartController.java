package com.Ecommerce.acme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.Ecommerce.acme.model.Cart;
import com.Ecommerce.acme.model.Order;
import com.Ecommerce.acme.model.User;
import com.Ecommerce.acme.service.CartService;

@Controller
public class CartController {

	@Autowired
	private CartService cs;

	@GetMapping({"/cart"})
	public String showCart(Model model, Authentication authentication ) throws Exception {
		return  cs.getSelectionOfCurrentUser(model, authentication);
	}

	@PostMapping("/cart")
	public String CreateOrder(Cart cart, Order order, User user, Authentication authentication) {
		return cs.submitCartForm(cart, order, user, authentication);
	}

}
