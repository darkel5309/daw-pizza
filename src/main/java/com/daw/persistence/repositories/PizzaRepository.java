package com.daw.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Pizza;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {

}
