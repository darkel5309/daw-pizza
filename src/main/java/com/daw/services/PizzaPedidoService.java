package com.daw.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.entities.PizzaPedido;
import com.daw.persistence.repositories.PizzaPedidoRepository;
import com.daw.services.dto.PizzaPedidoInputDTO;
import com.daw.services.dto.PizzaPedidoOutputDTO;
import com.daw.services.mappers.PizzaPedidoMapper;

@Service
public class PizzaPedidoService {

	@Autowired
	private final PizzaPedidoRepository pizzaPedidoRepository;

	public PizzaPedidoService(PizzaPedidoRepository pizzaPedidoRepository) {
		super();
		this.pizzaPedidoRepository = pizzaPedidoRepository;
	}

	@Autowired
	private PizzaService pizzaService;

	//CRUDs simples
	public List<PizzaPedido> findAll() {
		return this.pizzaPedidoRepository.findAll();
	}

	public boolean existsPizzaPedido(int idPizzaPedido) {
		return this.pizzaPedidoRepository.existsById(idPizzaPedido);
	}

	public Optional<PizzaPedido> findById(int idPizzaPedido) {
		return this.pizzaPedidoRepository.findById(idPizzaPedido);
	}

	// Crear el pizzaPedido
	public PizzaPedido create(PizzaPedido pizzaPedido) {
		return this.pizzaPedidoRepository.save(pizzaPedido);
	}

	// Actualizar el precio del pizzaPedido
	public PizzaPedido save(PizzaPedido pizzaPedido) {
		return this.pizzaPedidoRepository.save(pizzaPedido);
	}

	// eliminar una pizza
	public boolean delete(int idPizzaPedido) {
		boolean result = false;

		if (this.pizzaPedidoRepository.existsById(idPizzaPedido)) {
			this.pizzaPedidoRepository.deleteById(idPizzaPedido);
			result = true;
		}
		return result;
	}

	//CRUDs de PizzaPedidoDTO
	public List<PizzaPedidoOutputDTO> findByIdPedido(int idPedido) {
		List<PizzaPedidoOutputDTO> dtos = new ArrayList<PizzaPedidoOutputDTO>();

		for (PizzaPedido pp : this.pizzaPedidoRepository.findByIdPedido(idPedido)) {
			dtos.add(PizzaPedidoMapper.toDTO(pp));
		}

		return dtos;
	}

	public PizzaPedidoOutputDTO findDTO(int idPizza) {
		PizzaPedido pp = this.pizzaPedidoRepository.findById(idPizza).get();

		return PizzaPedidoMapper.toDTO(pp);
	}

	public PizzaPedidoOutputDTO save(PizzaPedidoInputDTO inputDTO) {
		PizzaPedido entity = PizzaPedidoMapper.toEntity(inputDTO);

		// nos traemos la pizza
		Pizza pizza = this.pizzaService.findById(entity.getIdPizza()).get();
		entity.setPrecio(entity.getCantidad() * pizza.getPrecio());

		entity = this.pizzaPedidoRepository.save(entity);

		//Añadimos la pizza que viene a nula cuando hacemos el save() para que no de NullPointer en el Mapper
		entity.setPizza(pizza);

		return PizzaPedidoMapper.toDTO(entity);
	}
}
