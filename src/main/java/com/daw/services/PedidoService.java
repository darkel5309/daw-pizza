package com.daw.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pedido;
import com.daw.persistence.entities.PizzaPedido;
import com.daw.persistence.repositories.PedidoRepository;
import com.daw.services.dto.PedidoDTO;
import com.daw.services.dto.PizzaPedidoInputDTO;
import com.daw.services.dto.PizzaPedidoOutputDTO;
import com.daw.services.mappers.PedidoMapper;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PizzaPedidoService pizzaPedidoService;

	// CRUDs
	public List<PedidoDTO> findAll() {
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();

		for (Pedido p : this.pedidoRepository.findAll()) {
			pedidosDTO.add(PedidoMapper.toDto(p));
		}

		return pedidosDTO;
	}

	public PedidoDTO findById(int idPedido) {
		return PedidoMapper.toDto(this.pedidoRepository.findById(idPedido).get());
	}

	public Optional<Pedido> findByIdEntity(int idPedido) {
		return this.pedidoRepository.findById(idPedido);
	}

	public boolean existsPedido(int idPedido) {
		return this.pedidoRepository.existsById(idPedido);
	}

	public PedidoDTO create(Pedido pedido) {
		pedido.setFecha(LocalDateTime.now());
		pedido.setTotal(0.0);

		pedido = this.pedidoRepository.save(pedido);

		pedido.setCliente(this.clienteService.findById(pedido.getIdCliente()).get());
		pedido.setPizzaPedidos(new ArrayList<PizzaPedido>());

		return PedidoMapper.toDto(pedido);
	}

	// Lo mismo prácticamente
	public Pedido update(Pedido pedido) {
		return this.pedidoRepository.save(pedido);
	}

	public boolean delete(int idPedido) {
		boolean result = false;

		if (this.pedidoRepository.existsById(idPedido)) {
			this.pedidoRepository.deleteById(idPedido);
			result = true;
		}
		return result;
	}

	public PizzaPedidoOutputDTO addModPizza(PizzaPedidoInputDTO inputDTO) {
		PizzaPedidoOutputDTO outDTO = this.pizzaPedidoService.save(inputDTO);

		this.actualizarTotal(inputDTO.getIdPedido());

		return outDTO;
	}

	public void actualizarTotal(int idPedido) {
		Pedido pedido = this.pedidoRepository.findById(idPedido).get();
		Double total = 0.00;

		for (PizzaPedido pp : pedido.getPizzaPedidos()) {
			total += pp.getPrecio();
		}

		pedido.setTotal(total);

		this.pedidoRepository.save(pedido);
	}

	// DELETE PIZZA
	// Método intermedio que llame al pizzaPedidoService y lo borre
	// Actualizamos precio
	// Devolvemos un true si se ha borrado
	public boolean deletePizza(int idPizzaPedido) {
		boolean result = this.pizzaPedidoService.delete(idPizzaPedido);

		if (result) {
			this.actualizarTotal(idPizzaPedido);
		}

		return result;
	}

}
