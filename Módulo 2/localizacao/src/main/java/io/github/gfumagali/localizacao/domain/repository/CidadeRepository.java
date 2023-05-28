package io.github.gfumagali.localizacao.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import io.github.gfumagali.localizacao.domain.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {
    Page<Cidade> findByNome(String nome, Pageable pageable);
    @Query("SELECT c FROM Cidade c WHERE upper(c.nome) LIKE upper(?1)") List<Cidade> findByNomeLike(String nome);
    List<Cidade> findByNomeStartingWith(String nome);
    List<Cidade> findByNomeEndingWith(String nome);
    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(long habitantes);
    List<Cidade> findByHabitantesLessThan(long habitantes);
    List<Cidade> findByHabitantesLessThanEqual(long habitantes);
    List<Cidade> findByHabitantesGreaterThan(long habitantes);
    List<Cidade> findByHabitantesBetween(long habitantes1, long habitantes2);

    List<Cidade> findByHabitantesLessThanAndNomeLike(long habitantes, String nome);

     

}
