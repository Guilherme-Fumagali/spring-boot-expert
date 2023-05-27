package io.github.gfumagali.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.github.gfumagali.domain.entity.Usuario;
import io.github.gfumagali.domain.repository.Usuarios;
import jakarta.transaction.Transactional;
import io.github.gfumagali.exception.SenhaInvalidaException;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private Usuarios repository;

    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        
        String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User.builder()
            .username(usuario.getLogin())
            .password(usuario.getSenha())
            .roles(roles)
            .build(); 
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = new BCryptPasswordEncoder().matches(usuario.getSenha(), user.getPassword());
        if(senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    
}
