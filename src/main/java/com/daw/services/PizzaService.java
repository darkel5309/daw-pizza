package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.repositories.PizzaRepository;

@Service
public class PizzaService {
	
	private final PizzaRepository pizzaRepository;
	
	public PizzaService(PizzaRepository pizzaCrudRepository) {
		super();
		this.pizzaRepository = pizzaCrudRepository;
	}

	public List<Pizza> getAll(){
		return this.pizzaRepository.findAll();
	}
	
	public Optional<Pizza> getPizza(int idPizza){
		return this.pizzaRepository.findById(idPizza);
	}
	
	public Pizza create(Pizza pizza) {
		pizza.setDisponible(true);
		
		return this.pizzaRepository.save(pizza);
	}
	
	public Pizza save(Pizza pizza) {
		return this.pizzaRepository.save(pizza);
	}
	
	public boolean delete(int idPizza) {
		boolean result = false;
		
		if (this.pizzaRepository.findById(idPizza).isPresent()) {
			this.pizzaRepository.deleteById(idPizza);
			
			result = true;
		} 
		return result;
	}
	
	public boolean exists(int idPizza) {
		return this.pizzaRepository.existsById(idPizza);
	}
	
}
