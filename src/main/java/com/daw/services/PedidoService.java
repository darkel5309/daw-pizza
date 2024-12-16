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
import com.daw.services.mappers.PedidoMapper;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteService clienteService;

	//CRUDs
	public List<PedidoDTO> findAll() {
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		
		for (Pedido p : this.pedidoRepository.findAll()) {
			pedidosDTO.add(PedidoMapper.toDto(p));
		}
		
		return pedidosDTO;
	}
	
	public Optional<Pedido> findById(int idPedido) {
		return this.pedidoRepository.findById(idPedido);
	}

	public PedidoDTO create(Pedido pedido) {
		pedido.setFecha(LocalDateTime.now());
		pedido.setTotal(0.0);
		
		pedido = this.pedidoRepository.save(pedido);
		
		pedido.setCliente(this.clienteService.getCliente(pedido.getIdCliente()).get());
		pedido.setPizzaPedidos(new ArrayList<PizzaPedido>());
		
		return PedidoMapper.toDto(pedido);
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

	public boolean existsPedido(int idPedido) {
		return this.pedidoRepository.existsById(idPedido);
	}

}
