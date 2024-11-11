package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.repositories.PizzaCrudRepository;

@Service
public class PizzaService {
	
	private final PizzaCrudRepository pizzaCrudRepository;
	
	public PizzaService(PizzaCrudRepository pizzaCrudRepository) {
		super();
		this.pizzaCrudRepository = pizzaCrudRepository;
	}

	public List<Pizza> getAll(){
		return (List<Pizza>)this.pizzaCrudRepository.findAll();
	}
	
	public Optional<Pizza> getPizza(int idPizza){
		return this.pizzaCrudRepository.findById(idPizza);
	}
	
	public Pizza create(Pizza pizza) {
		pizza.setDisponible(true);
		
		return this.pizzaCrudRepository.save(pizza);
	}
	
	public Pizza save(Pizza pizza) {
		return this.pizzaCrudRepository.save(pizza);
	}
	
	public boolean delete(int idPizza) {
		boolean result = false;
		
		if (this.pizzaCrudRepository.findById(idPizza).isPresent()) {
			this.pizzaCrudRepository.deleteById(idPizza);
			
			result = true;
		} 
		return result;
	}
	
	public boolean exists(int idPizza) {
		return this.pizzaCrudRepository.existsById(idPizza);
	}
	
}
