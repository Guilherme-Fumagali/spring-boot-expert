package io.github.gfumagali.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@Getter @Setter @ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    public Cliente() {}

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
