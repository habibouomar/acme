package com.Ecommerce.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Ecommerce.acme.model.Selection;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SelectionRepository extends JpaRepository<Selection, Integer> {

}
