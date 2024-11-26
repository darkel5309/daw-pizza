package com.daw.services.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDTO {

	private Integer id;
	private LocalDateTime fecha;
	private Double total;
	private String metodo;
	private String cliente;
	private String telefono;
	private String direccion;
	private String notas;
	private List<PizzaPedidoOutputDTO> pizzas;
}
