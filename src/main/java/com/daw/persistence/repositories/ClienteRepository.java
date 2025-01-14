package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Cliente;

public interface ClienteRepository extends ListCrudRepository<Cliente, Integer> {

	List<Cliente> findByTelefonoContaining(String telefono);

	// @Query("select c, count(p) as pedidos"
	// + "from Cliente c "
	// + "left join c.pedidos p "
	// + "group by c "
	// + "order by pedidos desc "
	// + "limit 3" )
	
	// List<Object[]> findTop3Clientes();

}
