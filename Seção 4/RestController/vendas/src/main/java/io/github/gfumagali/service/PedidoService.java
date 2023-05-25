package io.github.gfumagali.service;

import io.github.gfumagali.domain.entity.Pedido;
import io.github.gfumagali.rest.dto.PedidoDTO;

public interface PedidoService {
    public Pedido save(PedidoDTO dto);
    
}
