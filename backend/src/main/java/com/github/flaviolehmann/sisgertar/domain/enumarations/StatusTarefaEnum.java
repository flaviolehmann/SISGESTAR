package com.github.flaviolehmann.sisgertar.domain.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusTarefaEnum {

    A_FAZER(1L, "A Fazer"),
    FAZENDO(2L, "Fazendo"),
    PAUSADA(3L, "Pausada"),
    FEITO(4L, "Feito");

    private Long id;
    private String descricao;
}
