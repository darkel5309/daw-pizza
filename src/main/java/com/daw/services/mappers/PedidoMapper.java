package com.daw.services.mappers;

import java.util.ArrayList;
import java.util.List;

import com.daw.persistence.entities.Pedido;
import com.daw.persistence.entities.PizzaPedido;
import com.daw.services.dto.PedidoDTO;
import com.daw.services.dto.PizzaPedidoOutputDTO;

public class PedidoMapper {
	public static PedidoDTO toDto(Pedido pedido) {
		PedidoDTO dto = new PedidoDTO();

		// Vamos recogiendo los atributos de PedidoDTO de las distintas entidades
		// Vienen de Pedido
		dto.setId(pedido.getId());
		dto.setFecha(pedido.getFecha());
		dto.setTotal(pedido.getTotal());
		dto.setMetodo(pedido.getMetodo());
		dto.setNotas(pedido.getNotas());

		// Vienen del Cliente asociado al Pedido
		dto.setCliente(pedido.getCliente().getNombre());
		dto.setTelefono(pedido.getCliente().getTelefono());
		dto.setDireccion(pedido.getCliente().getDireccion());

		// creo una lista PizzaPedidoDTO
		List<PizzaPedidoOutputDTO> pizzas = new ArrayList<PizzaPedidoOutputDTO>();

		for (PizzaPedido pp : pedido.getPizzaPedidos()) {
			pizzas.add(PizzaPedidoMapper.toDTO(pp));
		}

		dto.setPizzas(pizzas);

		return dto;
	}
}
