package io.github.gfumagali.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gfumagali.domain.entity.Cliente;
import io.github.gfumagali.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
