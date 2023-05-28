package io.github.gfumagali.localizacao.domain.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import io.github.gfumagali.localizacao.domain.entity.Cidade;

public abstract class CidadeSpecs {
    public static Specification<Cidade> nomeEqual(String nome){
        return (root, query, builder) -> builder.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> nomeLike(String nome){
        return (root, query, builder) -> builder.like(builder.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
    }

    public static Specification<Cidade> habitantesGreaterThan(long habitantes){
        return (root, query, builder) -> builder.greaterThan(root.get("habitantes"), habitantes);
    }

    public static Specification<Cidade> propertyEqual(String property, Object value){
        return (root, query, builder) -> builder.equal(root.get(property), value);
    }

    public static Specification<Cidade> habitantesBetween(long min, long max){
        return (root, query, builder) -> builder.between(root.get("habitantes"), min, max);
    }   
}
