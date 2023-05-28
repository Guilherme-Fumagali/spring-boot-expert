package io.github.gfumagali.localizacao.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.github.gfumagali.localizacao.domain.entity.Cidade;
import io.github.gfumagali.localizacao.domain.repository.CidadeRepository;
import static io.github.gfumagali.localizacao.domain.repository.specs.CidadeSpecs.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public Page<Cidade> listarCidadesPorNome(String nome) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("habitantes", "nome"));
        return cidadeRepository.findByNome(nome, pageable);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return cidadeRepository.findAll(example);
    }

    public void listarCidadesByNomeSpecs(){
        cidadeRepository.findAll(nomeEqual("São Paulo").and(habitantesGreaterThan(1000L)))
        .forEach(System.out::println);   
    }

    /* public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> spec = Specification.where((root, query, builder) -> builder.conjunction());

        if(filtro.getId() != null){
            spec = spec.and(propertyEqual("id", filtro.getId()));
        }

        if(String.valueOf(filtro.getNome()) != null){
            spec = spec.and(nomeLike(filtro.getNome()));
        }   

        if(filtro.getHabitantes() != null){
            spec = spec.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

    } */

}
