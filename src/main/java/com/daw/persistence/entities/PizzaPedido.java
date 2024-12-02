package com.daw.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza_pedido")
@Getter
@Setter
@NoArgsConstructor
public class PizzaPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "id_pedido")
	private Integer idPedido;
	
	@Column(name = "id_pizza")
	private Integer idPizza;
	
	@Column(columnDefinition = "DECIMAL(2,1)")
	private Double cantidad;
	
	@Column(columnDefinition = "DECIMAL(5,2)")
	private Double precio;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id", insertable = false, updatable = false)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_pizza", referencedColumnName = "id", insertable = false, updatable = false)
	private Pizza pizza;
}