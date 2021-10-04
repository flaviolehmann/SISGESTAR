package com.github.flaviolehmann.sisgertar.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "O status em questão não existe.")
public class StatusInexistenteException extends RuntimeException {
}
