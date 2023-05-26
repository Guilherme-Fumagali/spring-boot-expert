package io.github.gfumagali.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;


import io.github.gfumagali.domain.entity.Usuario;
import io.github.gfumagali.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder encoder;

    @PostMapping()
    @ResponseBody   
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioService.save(usuario);
    }
}
