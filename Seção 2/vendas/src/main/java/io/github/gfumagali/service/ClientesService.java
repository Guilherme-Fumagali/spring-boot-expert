package io.github.gfumagali.service;

import org.springframework.stereotype.Service;

import io.github.gfumagali.model.Cliente;
import io.github.gfumagali.repository.ClientesRepository;

@Service
public class ClientesService {
    private ClientesRepository repository;

    public ClientesService(ClientesRepository repository){
        this.repository = repository;
    }

    public void salvarCliente(Cliente c) {
        verificarCliente(c);
        this.repository.persistir(c);
    }

    public void verificarCliente(Cliente c) { /* Realiza verificações */ }
}
