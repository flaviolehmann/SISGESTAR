package com.github.flaviolehmann.sisgertar.domain.enumarations;

import com.github.flaviolehmann.sisgertar.service.error.StatusInexistenteException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusTarefaEnum {

    A_FAZER(1L, "A Fazer"),
    FAZENDO(2L, "Fazendo"),
    PAUSADA(3L, "Pausada"),
    FEITO(4L, "Feito");

    private Long id;
    private String descricao;

    public static StatusTarefaEnum obterPorId(Long id) {
        return Arrays.stream(StatusTarefaEnum.values())
                .filter(enumeration -> id.equals(enumeration.getId()))
                .findFirst().orElseThrow(StatusInexistenteException::new);
    }
}
