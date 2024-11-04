package com.daw.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Pizza;
import com.daw.services.PizzaService;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

	private final PizzaService pizzaService;
	
	public PizzaController(PizzaService pizzaService) {
		super();
		this.pizzaService = pizzaService;
	}

	public ResponseEntity <List<Pizza>> list(){
		return ResponseEntity.ok(this.pizzaService.getAll());
	}
	
	@GetMapping("/{idPizza}")
	public ResponseEntity<Pizza> findOne(@PathVariable int idPizza){
		Optional<Pizza> optPizza = this.pizzaService.getPizza(idPizza);
		
		//el id existe en la base de datos 200ok y la pizza
		if(optPizza.isPresent()){
			return ResponseEntity.ok(optPizza.get());
		}
		//el id no existe en la base de datos notfound
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Pizza> delete(@PathVariable int idPizza){
		if (this.pizzaService.delete(idPizza)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Pizza> create(@RequestBody Pizza pizza){
		return new ResponseEntity<Pizza>(this.pizzaService.save(pizza), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Pizza> update(@PathVariable (idPizza) @RequestBody Pizza pizza){
		return new ResponseEntity<Pizza>(this.pizzaService.save(pizza), HttpStatus.CREATED);
	}
	
}
