package com.Ecommerce.acme.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.Ecommerce.acme.model.Order;
import com.Ecommerce.acme.model.Selection;
import com.Ecommerce.acme.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository or;

	@Autowired
	private SelectionService ss;

	@Autowired
	private UserService us;

	public Iterable<Order> getAllOrders() { return or.findAll(); }

	public void insertOrder(Order Order) { or.save(Order); }

	public String getAllOrdersByUser(Authentication authentication, Model model) {

		int currentUserId = getCurrentUserId(authentication);
		ArrayList<Order> userOrderList = getUserOrders(currentUserId);

		model.addAttribute("orders", userOrderList);

		return "order";
	}

	public void getDetailSelectionById(int Id_order, Model model) {	

		ArrayList<Selection> detailsList = new ArrayList<Selection>();

		for(Selection selection : ss.getAllSelection()) {

				if(selection.getCart() != null && (selection.getCart().getId_cart() == Id_order) ) {

					detailsList.add(selection);
				}			
		}

		model.addAttribute("details", detailsList);
	}

	public void getUserProfileData(Authentication authentication, Model model) {

		int currentUserId = getCurrentUserId(authentication);
		ArrayList<Order> userOrderList = getUserOrders(currentUserId);
		int totalOrderCurrentUser = userOrderList.size();
		double TurnoverCurrentUser = 0;
		int numberOfOrdersInVerification = 0;
		String firstOrder = "vide";
		String lastOrder = "vide";

		for(Order order : userOrderList) {
			TurnoverCurrentUser += order.getCart().getTotal_price();

			if(!order.isValidate() && order.getUser().getId_user() == currentUserId){
				numberOfOrdersInVerification += 1;
			}
		}

		if (!userOrderList.isEmpty()) {
			firstOrder = userOrderList.get(0).getOrder_date();
			lastOrder = userOrderList.get(userOrderList.size() - 1).getOrder_date();
		}

		model.addAttribute("person", us.findByUsername(authentication.getName()));
		model.addAttribute("data", totalOrderCurrentUser);
		model.addAttribute("data1", TurnoverCurrentUser);
		model.addAttribute("data2", firstOrder);
		model.addAttribute("data3", lastOrder);
		model.addAttribute("data4", numberOfOrdersInVerification);
	}

	private int getCurrentUserId(Authentication authentication) {
		return us.findByUsername(authentication.getName()).getId_user();
	}

	private ArrayList<Order> getUserOrders(int currentUserId) {
		ArrayList<Order> userOrderList = new ArrayList<>();
		for (Order order : this.getAllOrders()) {
			if (order.getUser().getId_user() == currentUserId) {
				userOrderList.add(order);
			}
		}
		return userOrderList;
	}
}