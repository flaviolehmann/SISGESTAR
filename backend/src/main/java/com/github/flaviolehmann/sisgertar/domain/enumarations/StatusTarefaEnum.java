package com.github.flaviolehmann.sisgertar.domain.enumarations;

import com.github.flaviolehmann.sisgertar.service.error.AlteracaoStatusTarefaInvalidoException;
import com.github.flaviolehmann.sisgertar.service.error.StatusInexistenteException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusTarefaEnum {

    A_FAZER(1L, "A Fazer") {

        @Override
        public void validarProximoStatus(StatusTarefaEnum proximoStatus) {
            if (!proximoStatus.equals(FAZENDO)) {
                throw new AlteracaoStatusTarefaInvalidoException();
            }
        }
    },

    FAZENDO(2L, "Fazendo") {

        @Override
        public void validarProximoStatus(StatusTarefaEnum proximoStatus) {
            if (proximoStatus.equals(A_FAZER)) {
                throw new AlteracaoStatusTarefaInvalidoException();
            }
        }
    },

    PAUSADA(3L, "Pausada") {

        @Override
        public void validarProximoStatus(StatusTarefaEnum proximoStatus) {
            if (!proximoStatus.equals(FAZENDO)) {
                throw new AlteracaoStatusTarefaInvalidoException();
            }
        }
    },

    FEITO(4L, "Feito") {

        @Override
        public void validarProximoStatus(StatusTarefaEnum proximoStatus) {
            throw new AlteracaoStatusTarefaInvalidoException();
        }
    };

    private Long id;
    private String descricao;

    public abstract void validarProximoStatus(StatusTarefaEnum proximoStatus);

    public static StatusTarefaEnum obterPorId(Long id) {
        return Arrays.stream(StatusTarefaEnum.values())
                .filter(enumeration -> id.equals(enumeration.getId()))
                .findFirst().orElseThrow(StatusInexistenteException::new);
    }
}
