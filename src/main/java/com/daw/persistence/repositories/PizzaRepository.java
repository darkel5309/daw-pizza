package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Pizza;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
	
	// List<Pizza> findPizzaOrderByPrecioAsc();

}
