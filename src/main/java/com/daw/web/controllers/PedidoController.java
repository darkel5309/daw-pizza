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
import com.daw.services.PedidoService;
import com.daw.services.dto.PedidoDTO;
import com.daw.services.dto.PizzaPedidoOutputDTO;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}

	// cruds de pedido
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> list() {
		return ResponseEntity.ok(this.pedidoService.getAll());
	}

	@GetMapping("/{idPedido}")
	public ResponseEntity<Pedido> findOne(@PathVariable int idPedido) {
		Optional<Pedido> optPedido = this.pedidoService.getPedido(idPedido);

		if (optPedido.isPresent()) {
			return ResponseEntity.ok(optPedido.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
		return new ResponseEntity<Pedido>(this.pedidoService.create(pedido), HttpStatus.CREATED);
	}

	@PutMapping("/{idPedido}")
	public ResponseEntity<Pedido> update(@PathVariable int idPedido, @RequestParam Pedido pedido) {
		if (idPedido != pedido.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if (!this.pedidoService.exists(idPedido)) {
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
	
	// cruds de pizzapedido
	@GetMapping("/{idPedido}/pizzas")
	public ResponseEntity<List<PizzaPedidoOutputDTO>> listPizzas(@PathVariable int idPedido){
		return ResponseEntity.ok(this.pizzaPedidoService)
	}

}
