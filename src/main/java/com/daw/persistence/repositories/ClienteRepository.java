package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Cliente;

public interface ClienteRepository extends ListCrudRepository<Cliente, Integer> {

	List<Cliente> findByTelefonoContaining(String telefono);
}
