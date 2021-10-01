package com.github.flaviolehmann.sisgertar.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "A tarefa encontrada não possui registro em banco.")
public class TarefaNaoEncontradaException extends RuntimeException {
}
