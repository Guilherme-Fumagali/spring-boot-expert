package io.github.gfumagali.localizacao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cidade")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Cidade {
    
    @Id
    @Column
    private long id;

    @Column(length = 50)
    private String nome;

    @Column(name = "qtd_habitantes")
    private long habitantes;
}
