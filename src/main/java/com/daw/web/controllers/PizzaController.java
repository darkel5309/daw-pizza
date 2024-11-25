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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Pizza;
import com.daw.services.PizzaService;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private final PizzaService pizzaService;

	public PizzaController(PizzaService pizzaService) {
		super();
		this.pizzaService = pizzaService;
	}

	@GetMapping
	public ResponseEntity<List<Pizza>> list() {
		return ResponseEntity.ok(this.pizzaService.getAll());
	}

	@GetMapping("/{idPizza}")
	public ResponseEntity<Pizza> findOne(@PathVariable int idPizza) {
		Optional<Pizza> optPizza = this.pizzaService.getPizza(idPizza);

		// el id existe en la base de datos 200ok y la pizza
		if (optPizza.isPresent()) {
			return ResponseEntity.ok(optPizza.get());
		}
		// el id no existe en la base de datos notfound
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Pizza> create(@RequestBody Pizza pizza) {
		return new ResponseEntity<Pizza>(this.pizzaService.create(pizza), HttpStatus.CREATED);
	}

	@PutMapping("/{idPizza}")
	public ResponseEntity<Pizza> update(@PathVariable int idPizza, @RequestBody Pizza pizza) {
		if (idPizza != pizza.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if (!this.pizzaService.exists(idPizza)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(this.pizzaService.save(pizza));
	}

	@DeleteMapping("/{idPizza}")
	public ResponseEntity<Pizza> delete(@PathVariable int idPizza) {
		if (this.pizzaService.delete(idPizza)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/carta")
	public ResponseEntity<List<Pizza>> carta() {
		return ResponseEntity.ok(this.pizzaService.getCarta());
	}

	@GetMapping("/nombre")
	public ResponseEntity<List<Pizza>> findByNombre(@RequestParam String nombre) {
		return ResponseEntity.ok(this.pizzaService.getByNombre(nombre));
	}

	@GetMapping("/conIngrediente")
	public ResponseEntity<List<Pizza>> findIngrediente(@RequestParam String ingrediente) {
		return ResponseEntity.ok(this.pizzaService.getIngrediente(ingrediente));
	}

	@GetMapping("/sinIngrediente")
	public ResponseEntity<List<Pizza>> findSinIngrediente(@RequestParam String ingrediente) {
		return ResponseEntity.ok(this.pizzaService.getSinIngrediente(ingrediente));
	}

	@PutMapping("/{idPizza}/precio")
	// pathvariable para encontrar la entidad con el id y el requestparam lo que
	// solicitamos
	public ResponseEntity<Pizza> updatePrecio(@PathVariable int idPizza, @RequestParam Double nuevoPrecio) {
		if (this.pizzaService.exists(idPizza)) {
			return ResponseEntity.ok(this.pizzaService.actualizarPrecio(idPizza, nuevoPrecio));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{idPizza}/disponible")
	public ResponseEntity<Pizza> marcarDesmarcarDisponible(@PathVariable int idPizza) {
		if (this.pizzaService.exists(idPizza)) {
			return ResponseEntity.ok(this.pizzaService.marcarDesmarcarDisponible(idPizza));
		}
		return ResponseEntity.notFound().build();
	}
}
