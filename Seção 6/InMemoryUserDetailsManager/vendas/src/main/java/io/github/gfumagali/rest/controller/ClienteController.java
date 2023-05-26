package io.github.gfumagali.rest.controller;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

import io.github.gfumagali.domain.entity.Cliente;
import io.github.gfumagali.domain.repository.Clientes;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private Clientes repository;

    public ClienteController(Clientes repository){
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return repository
            .findById(id)
            .orElseThrow(() -> 
                new ResponseStatusException(NOT_FOUND, "Cliente não encontrado")
            );
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return repository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.findById(id).map(cliente -> {
            repository.delete(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
    }
   
    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente){
        repository.findById(id).map(clienteExistente -> {
                cliente.setId(clienteExistente.getId());
                repository.save(cliente);
                return clienteExistente;
            }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping()
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Cliente> example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }
}
