package io.github.gfumagali.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.gfumagali.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class Clientes {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente save(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente update(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void delete(Cliente cliente){
        if(!entityManager.contains(cliente))
            cliente = entityManager.merge(cliente);

        entityManager.remove(cliente);
    }

    @Transactional
    public void delete(Integer id){
        this.delete(entityManager.find(Cliente.class, id));
    }

    @Transactional(readOnly = true)
    public List<Cliente> getByName(String nome){
        String jpql = "select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> getAll(){
        return entityManager
            .createQuery("from Cliente", Cliente.class)
            .getResultList();
    }
}
