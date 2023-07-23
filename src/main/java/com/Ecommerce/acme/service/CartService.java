package com.Ecommerce.acme.service;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.Ecommerce.acme.model.Cart;
import com.Ecommerce.acme.model.Order;
import com.Ecommerce.acme.model.Selection;
import com.Ecommerce.acme.model.User;
import com.Ecommerce.acme.repository.CartRepository;
import lombok.Data;
import javax.sql.DataSource;

@Data
@Service
public class CartService {

	@Autowired
	private UserService us;

	@Autowired
	private SelectionService ss;

	@Autowired
	private OrderService os;

	@Autowired
	private CartRepository cr;

	@Autowired
	private DataSource dataSource;

	public void insertCart(Cart Cart) {
		cr.save(Cart);
	}

	public String getSelectionOfCurrentUser(Model model, Authentication authentication )  throws Exception {

		int currentUserId = us.findByUsername(authentication.getName()).getId_user();
		ArrayList<Selection> list = new ArrayList<Selection>();
		double sum = 0;

		try( Connection con = dataSource.getConnection();
			 Statement st = con.createStatement();
			 ResultSet res = st.executeQuery("SELECT SUM(total) FROM selection WHERE id_cart is null" + " AND id_user = " + currentUserId )) {

			for (Selection s : ss.getAllSelection()) {
				if (s.getCart() == null && s.getId_user() == currentUserId) {
					list.add(s);
				}
			}

			model.addAttribute("selections", list);
			model.addAttribute("person", us.findByUsername(authentication.getName()));

			while (res.next()) {
				double c = res.getDouble(1);
				sum += c;
			}

			model.addAttribute("finalPrice", sum);

		} catch (SQLException e) {
			e.printStackTrace();
				return "error";
		}

		return "cart";

	}

	public String submitCartForm(@ModelAttribute("cartForm") Cart cart, Order order, User user, Authentication authentication) {

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);

		int currentUserId = us.findByUsername(authentication.getName()).getId_user();

		cart.setUser(user);
		insertCart(cart);

		for(Selection s : ss.getAllSelection()) {

			if(s.getCart() == null  && s.getId_user() == currentUserId) {

				s.setCart(cart);
				ss.insertSelection(s);
			}
		}

		order.setCart(cart);
		order.setOrder_date(formattedDate);
		order.setUser(user);
		order.setValidate(false);

		os.insertOrder(order);

		return "redirect:/cart";
	}

}
