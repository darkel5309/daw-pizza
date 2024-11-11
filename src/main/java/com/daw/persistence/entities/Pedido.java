package com.daw.persistence.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "id_cliente")
	private Integer idCliente;
	
	@Column(columnDefinition = "DATETIME")
	private LocalDateTime fecha;
	
	@Column(columnDefinition = "DECIMAL(6,2)")
	private Double total;
	
	@Column(length = 1, columnDefinition = "CHAR")
	private String metodo;
	
	@Column(length = 200)
	private String notas;
	
	@ManyToOne
	@JoinColumn(name="id_cliente", referencedColumnName = "id", insertable=false, updatable=false)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<PizzaPedido> pizzaPedidos;
}
