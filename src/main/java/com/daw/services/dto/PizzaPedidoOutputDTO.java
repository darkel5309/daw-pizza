package com.daw.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PizzaPedidoOutputDTO {
	
	private Integer id;
	private Double cantidad;
	private Double precio;
	private Integer idPizza;
	private String pizza;
}
