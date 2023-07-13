package com.Ecommerce.acme.service;

import java.util.ArrayList;
import java.util.Optional;
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

	public Optional<Order> getOrder(final int id){
		return or.findById(id);
	}

	public Iterable<Order> getAllOrder() {
		return or.findAll();
	}

	public void deleteOrder(final int id) {
		or.deleteById(id);
	}

	public void insertOrder(Order Order) {
		or.save(Order);
	}

	public String getAllOrdersByUser(Authentication authentication, Model model) {

		int currentUserId = us.findByUsername(authentication.getName()).getId_user();
		ArrayList<Order> orderList = new ArrayList<Order>();

		for(Order o : this.getAllOrder()) {

			if( o.getUser().getId_user() == currentUserId) {

				orderList.add(o);

			}
		}

		model.addAttribute("orders", orderList);

		return "order";
	}

	public void getDetailSelectionById(int Id_order, Model model) {	

		ArrayList<Selection> detailsList = new ArrayList<Selection>();

		for(Selection s : ss.getAllSelection()) {
				
				if(s.getCart() != null && (s.getCart().getId_cart() == Id_order) ) {

					detailsList.add(s);
				}			
		}

		model.addAttribute("details", detailsList);
	}

	public void getDataProfil(Authentication authentication, Model model) {

		int currentUserId = us.findByUsername(authentication.getName()).getId_user();
		ArrayList<Order> orderList = new ArrayList<Order>();

		for(Order o : this.getAllOrder()) {

			if( o.getUser().getId_user() == currentUserId) {

				orderList.add(o);

			}
		}

		int totalOrderCurrentUser = orderList.size();
		double TurnoverCurrentUser = 0;
		int numberOfOrdersInVerification = 0;

		String firstOrder = "";
		String lastOrder = "";

		for(Order o : orderList) {
			TurnoverCurrentUser += o.getCart().getTotal_price();

			if(!o.isValidate() && o.getUser().getId_user() == currentUserId){
				numberOfOrdersInVerification += 1;
			}
		}

		if(orderList.size() == 0){
			 firstOrder = "vide";
			 lastOrder = "vide";
		}else{
			firstOrder = orderList.get(0).getOrder_date();
			lastOrder = orderList.get(orderList.size()-1).getOrder_date();
		}

		model.addAttribute("person", us.findByUsername(authentication.getName()));

		model.addAttribute("data", totalOrderCurrentUser);
		model.addAttribute("data1", TurnoverCurrentUser);
		model.addAttribute("data2", firstOrder);
		model.addAttribute("data3", lastOrder);
		model.addAttribute("data4", numberOfOrdersInVerification);

	}
}