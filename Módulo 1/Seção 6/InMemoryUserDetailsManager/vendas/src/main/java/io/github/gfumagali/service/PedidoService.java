package io.github.gfumagali.service;

import java.util.Optional;

import io.github.gfumagali.domain.entity.Pedido;
import io.github.gfumagali.domain.enums.StatusPedido;
import io.github.gfumagali.rest.dto.PedidoDTO;

public interface PedidoService {
    public Pedido save(PedidoDTO dto);

    public Optional<Pedido> getCompletePedido(Integer id);

    public void updateStatus(Integer id, StatusPedido status);
    
}
