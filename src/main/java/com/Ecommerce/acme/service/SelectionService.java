package com.Ecommerce.acme.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Ecommerce.acme.model.Selection;
import com.Ecommerce.acme.repository.SelectionRepository;
import lombok.Data;

@Data
@Service
public class SelectionService {
	
	@Autowired
	private SelectionRepository sr;

	public Iterable<Selection> getAllSelection() { return sr.findAll(); }

	public void deleteSelection(final int id) {
		sr.deleteById(id);
	}
	
	public void insertSelection(Selection Selection) {
		sr.save(Selection);
	}

}
