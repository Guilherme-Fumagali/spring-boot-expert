package io.github.gfumagali.rest.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.github.gfumagali.domain.entity.ItemPedido;
import io.github.gfumagali.domain.entity.Pedido;
import io.github.gfumagali.domain.enums.StatusPedido;
import io.github.gfumagali.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.gfumagali.rest.dto.InformacoesItemPedidoDTO;
import io.github.gfumagali.rest.dto.InformacoesPedidoDTO;
import io.github.gfumagali.rest.dto.PedidoDTO;
import io.github.gfumagali.service.PedidoService;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        return service.save(dto).getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
            .getCompletePedido(id)
            .map(p -> converter(p))
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
            .builder()
            .codigo(pedido.getId())
            .cpf(pedido.getCliente().getCpf())
            .nomeCliente(pedido.getCliente().getNome())
            .total(pedido.getTotal())
            .dataPedido(pedido.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .status(pedido.getStatus().name())
            .items(converter(pedido.getItens()))
            .build();
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> items){
        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }

        return items
            .stream()
            .map(item -> InformacoesItemPedidoDTO
                .builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .preco(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build()
            ).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String status = dto.getNovoStatus();

        service.updateStatus(id, StatusPedido.valueOf(status));
    }
}
