package io.github.gfumagali.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gfumagali.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {}
