package io.github.gfumagali.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gfumagali.domain.entity.ItemPedido;

public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {}
