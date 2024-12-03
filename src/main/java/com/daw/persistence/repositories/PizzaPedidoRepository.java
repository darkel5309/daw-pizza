package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.PizzaPedido;

public interface PizzaPedidoRepository extends ListCrudRepository<PizzaPedido, Integer>{
	
	List<PizzaPedido> findByIdPedido(int idPedido);
}
