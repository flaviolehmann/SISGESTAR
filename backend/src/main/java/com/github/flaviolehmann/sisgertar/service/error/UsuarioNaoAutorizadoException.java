package com.github.flaviolehmann.sisgertar.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.UNAUTHORIZED,
        reason = "O usuário que está tentando alterar o status da tarefa não é possui autorização.")
public class UsuarioNaoAutorizadoException extends RuntimeException {
}
