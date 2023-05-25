package io.github.gfumagali.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.gfumagali.domain.entity.Cliente;
import io.github.gfumagali.domain.entity.ItemPedido;
import io.github.gfumagali.domain.entity.Pedido;
import io.github.gfumagali.domain.entity.Produto;
import io.github.gfumagali.domain.repository.Clientes;
import io.github.gfumagali.domain.repository.ItemsPedidos;
import io.github.gfumagali.domain.repository.Pedidos;
import io.github.gfumagali.domain.repository.Produtos;
import io.github.gfumagali.exception.RegraNegocioException;
import io.github.gfumagali.rest.dto.ItemPedidoDTO;
import io.github.gfumagali.rest.dto.PedidoDTO;
import io.github.gfumagali.service.PedidoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {
    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedidos itemsPedidosRepository;

    @Override
    @Transactional
    public Pedido save(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setData(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidosRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);

        return pedido;
    }
    
    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
        }

        return items
            .stream()
            .map(dto -> {
                Integer idProduto = dto.getProduto();
                Produto produto = produtosRepository
                    .findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setQuantidade(dto.getQuantidade());
                itemPedido.setPedido(pedido);
                itemPedido.setProduto(produto);
                return itemPedido;
            }).collect(Collectors.toList());  
    }

    @Override
    public Optional<Pedido> getCompletePedido(Integer id) {
        return pedidosRepository
            .findByIdFetchItems(id);
    }
}
