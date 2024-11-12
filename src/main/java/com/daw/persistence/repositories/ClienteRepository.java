package com.daw.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Cliente;

public interface ClienteRepository extends ListCrudRepository<Cliente, Integer> {

}
