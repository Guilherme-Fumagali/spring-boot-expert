package io.github.gfumagali.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pedido {
    private Integer id;
    private Cliente cliente;
    private LocalDate data;
    private BigDecimal total;
}
