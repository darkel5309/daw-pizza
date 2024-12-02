package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Cliente;
import com.daw.persistence.repositories.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> getAll() {
		return this.clienteRepository.findAll();
	}

	public Optional<Cliente> getCliente(int idCliente) {
		return this.clienteRepository.findById(idCliente);
	}

	public Cliente create(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	public Cliente save(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	public boolean delete(int idCliente) {
		boolean result = false;

		if (this.clienteRepository.findById(idCliente).isPresent()) {
			this.clienteRepository.deleteById(idCliente);

			result = true;
		}
		return result;
	}

	public boolean exists(int idCliente) {
		return this.clienteRepository.existsById(idCliente);
	}

	public List<Cliente> getByTelefono(String telefono) {
		return this.clienteRepository.findByTelefonoContaining(telefono);
	}

	public Cliente modificarDireccion(int idCliente, String nuevaDireccion) {
		Cliente cliente = this.clienteRepository.findById(idCliente).get();

		cliente.setDireccion(nuevaDireccion);
		return this.clienteRepository.save(cliente);
	}

}
