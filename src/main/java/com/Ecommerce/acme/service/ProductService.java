package com.Ecommerce.acme.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.Ecommerce.acme.model.Product;
import com.Ecommerce.acme.model.Selection;
import com.Ecommerce.acme.model.User;
import com.Ecommerce.acme.repository.ProductRepository;
import lombok.Data;

@Data
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository pr;
	
	@Autowired
	private UserService us;
	
	@Autowired
	private SelectionService ss;

	private static final List<Integer> quantityList = List.of(1, 10, 50, 100);
	private static final List<Integer> sizeList = List.of(41, 42, 43, 44, 45, 46);
	
	public Optional<Product> getProduct(final int id){
		return pr.findById(id);
	}

	public Iterable<Product> getAllProduct() {
		return pr.findAll();
	}

	public void deleteProduct(final int id) {
		pr.deleteById(id);
	}
	
	public void insertProduct(Product Product) {
		pr.save(Product);
	}	
	
	
	public String getListProduct(Model model) {
		model.addAttribute("products", getAllProduct());
		model.addAttribute("quantityList", quantityList);
		model.addAttribute("sizeList", sizeList);

		return "products";
	}
	
	public String submitSelectionForm(@ModelAttribute("selectionForm") Selection selection, Product product, Authentication authentication){

		User currentUser = us.findByUsername(authentication.getName());
		int currentUserMarge = currentUser.getMargin_rate();
		int currentUserId = currentUser.getId_user();

		Double marge = (currentUserMarge / 100.0);
		Double reduction = (product.getUnit_price() * marge);
		Double marginPrice = (product.getUnit_price() - reduction);
		double totalSelection = (marginPrice * selection.getQuantity());

		selection.setMargin_price(marginPrice);
		selection.setTotal(totalSelection);
		selection.setId_user(currentUserId);

		ss.insertSelection(selection);

		return "redirect:/products";
	}
	
}