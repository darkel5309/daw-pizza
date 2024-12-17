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

import com.daw.persistence.entities.Pedido;
import com.daw.services.ClienteService;
import com.daw.services.PedidoService;
import com.daw.services.PizzaPedidoService;
import com.daw.services.PizzaService;
import com.daw.services.dto.PedidoDTO;
import com.daw.services.dto.PizzaPedidoInputDTO;
import com.daw.services.dto.PizzaPedidoOutputDTO;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PizzaPedidoService pizzaPedidoService;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PizzaService pizzaService;

	// cruds de pedido
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> list() {
		return ResponseEntity.ok(this.pedidoService.findAll());
	}

	@GetMapping("/{idPedido}")
	public ResponseEntity<Pedido> findById(@PathVariable int idPedido) {
		Optional<Pedido> optPedido = this.pedidoService.findById(idPedido);

		if (optPedido.isPresent()) {
			return ResponseEntity.ok(optPedido.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<PedidoDTO> create(@RequestBody Pedido pedido) {
		if (this.clienteService.exists(pedido.getIdCliente())) {
			return ResponseEntity.notFound().build();

		}
		return new ResponseEntity<PedidoDTO>(this.pedidoService.create(pedido), HttpStatus.CREATED);
	}

	@PutMapping("/{idPedido}")
	public ResponseEntity<Pedido> update(@PathVariable int idPedido, @RequestParam Pedido pedido) {
		if (idPedido != pedido.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if (!this.pedidoService.existsPedido(idPedido)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(this.pedidoService.save(pedido));
	}

	@DeleteMapping("/{idPedido}")
	public ResponseEntity<Pedido> delete(@PathVariable int idPedido) {
		if (this.pedidoService.delete(idPedido)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	// CRUDs de PizzaPedido
	@GetMapping("/{idPedido}/pizzas")
	public ResponseEntity<List<PizzaPedidoOutputDTO>> listPizzas(@PathVariable int idPedido) {
		return ResponseEntity.ok(this.pizzaPedidoService.findByIdPedido(idPedido));
	}

	@GetMapping("/{idPedido}/pizzas/{idPizza}")
	public ResponseEntity<PizzaPedidoOutputDTO> findByIdPizza(@PathVariable int idPedido, @PathVariable int idPizza) {
		// comprobar que existe el pedido
		if (!this.pedidoService.existsPedido(idPedido) || !this.pizzaPedidoService.existsPizzaPedido(idPizza)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(this.pizzaPedidoService.findDTO(idPizza));
	}

	// añadir una pizza al pedido
	@PostMapping("/{idPedido}/pizzas")
	public ResponseEntity<PizzaPedidoOutputDTO> addPizza(@PathVariable int idPedido,
			@RequestBody PizzaPedidoInputDTO dto) {
		// comprobamos si existe el pedido
		if (!this.pedidoService.existsPedido(dto.getIdPizza())) {
			return ResponseEntity.notFound().build();
		}
		
		if (!this.pizzaService.exists(dto.getIdPizza())) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<PizzaPedidoOutputDTO>(this.pizzaPedidoService.create(dto),HttpStatus.CREATED);
	}
}
