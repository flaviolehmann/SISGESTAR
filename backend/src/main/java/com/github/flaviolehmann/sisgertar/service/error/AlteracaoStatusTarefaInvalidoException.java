package com.github.flaviolehmann.sisgertar.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "A alteração de status não é válida.")
public class AlteracaoStatusTarefaInvalidoException extends RuntimeException {
}
