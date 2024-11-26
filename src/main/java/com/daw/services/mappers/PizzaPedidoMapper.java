package com.daw.services.mappers;

import com.daw.persistence.entities.PizzaPedido;
import com.daw.services.dto.PizzaPedidoOutputDTO;

public class PizzaPedidoMapper {
	// estamos transformando a una salida DTO, por eso es output
	public static PizzaPedidoOutputDTO toDTO(PizzaPedido pizzaPedido) {
		PizzaPedidoOutputDTO dto = new PizzaPedidoOutputDTO();
		
		dto.setId(pizzaPedido.getId());
		dto.setIdPizza(pizzaPedido.getIdPizza());
		dto.setCantidad(pizzaPedido.getCantidad());
		dto.setPrecio(pizzaPedido.getPrecio());
		dto.setPizza(pizzaPedido.getPizza().getNombre());
		
		return dto;
	}
}
