package com.daw.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 30)
	private String nombre;
	
	@Column(length = 150)
	private String descripcion;
	
	@Column(columnDefinition = "DECIMAL(5,2)")
	private Double precio;
	
	@Column(columnDefinition = "TINYINT")
	private Boolean vegetariana;
	
	@Column(columnDefinition = "TINYINT")
	private Boolean vegana;
	
	@Column(columnDefinition = "TINYINT")
	private Boolean disponible;
	
}