package com.daw.persistence.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Pedido;

public interface PedidoRepository extends ListCrudRepository<Pedido, Integer>{

	List<Pedido> findByMetodoContaining(String metodo);
	List<Pedido> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
	List<Pedido> findByIdCliente(int idCliente);
}