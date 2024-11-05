package com.daw.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.daw.persistence.entities.Pizza;

public interface PizzaCrudRepository extends CrudRepository<Pizza, Integer> {

}
