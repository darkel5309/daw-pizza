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

import com.daw.persistence.entities.Cliente;
import com.daw.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> list() {
		return ResponseEntity.ok(this.clienteService.findAll());
	}

	@GetMapping("/{idCliente}")
	public ResponseEntity<Cliente> findById(@PathVariable int idCliente) {
		Optional<Cliente> optCliente = this.clienteService.findById(idCliente);

		if (optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(this.clienteService.create(cliente), HttpStatus.CREATED);
	}

	@PutMapping("/{idCliente}")
	public ResponseEntity<Cliente> update(@PathVariable int idCliente, @RequestBody Cliente cliente) {
		if (idCliente != cliente.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if (!this.clienteService.existsCliente(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(this.clienteService.save(cliente));
	}

	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Cliente> delete(@PathVariable int idCliente) {
		if (this.clienteService.delete(idCliente)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/telefono")
	public ResponseEntity<List<Cliente>> findByTelefono(@RequestParam String telefono) {
		return ResponseEntity.ok(this.clienteService.getByTelefono(telefono));
	}

	@PutMapping("/{idCliente}/direccion")
	public ResponseEntity<Cliente> updateDireccion(@PathVariable int idCliente, @RequestParam String direccion) {
		if (this.clienteService.existsCliente(idCliente)) {
			return ResponseEntity.ok(this.clienteService.modificarDireccion(idCliente, direccion));
		}
		return ResponseEntity.notFound().build();
	}

}