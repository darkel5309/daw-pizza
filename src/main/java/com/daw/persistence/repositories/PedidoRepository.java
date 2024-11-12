package com.daw.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Pedido;

public interface PedidoRepository extends ListCrudRepository<Pedido, Integer>{

}
