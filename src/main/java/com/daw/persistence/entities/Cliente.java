package com.daw.persistence.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 30, nullable = false)
	private String nombre;
	
	@Column(length = 100, nullable = false)
	private String direccion;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(length = 20, nullable = false, unique = true)
	private String telefono;
	
	// el cliente ve una lista de pedidos
	//cliente es el que hemos creado en pedido
	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Pedido> pedidos;

}
