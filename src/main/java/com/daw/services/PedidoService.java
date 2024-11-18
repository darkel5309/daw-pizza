package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pedido;
import com.daw.persistence.repositories.PedidoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;

	public PedidoService(PedidoRepository pedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
	}

	public List<Pedido> getAll() {
		return this.pedidoRepository.findAll();
	}

	public Optional<Pedido> getPedido(int idPedido) {
		return this.pedidoRepository.findById(idPedido);
	}

	public Pedido create(Pedido pedido) {
		return this.pedidoRepository.save(pedido);
	}

	public Pedido save(Pedido pedido) {
		return this.pedidoRepository.save(pedido);
	}

	public boolean delete(int idPedido) {
		boolean result = false;

		if (this.pedidoRepository.findById(idPedido).isPresent()) {
			this.pedidoRepository.deleteById(idPedido);

			result = true;
		}
		return result;
	}

	public boolean exists(int idPedido) {
		return this.pedidoRepository.existsById(idPedido);
	}

}
