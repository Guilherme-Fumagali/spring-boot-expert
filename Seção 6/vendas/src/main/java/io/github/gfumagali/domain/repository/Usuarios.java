package io.github.gfumagali.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gfumagali.domain.entity.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByLogin(String login);
}
