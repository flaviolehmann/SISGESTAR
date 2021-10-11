package com.github.flaviolehmann.sisgertar.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmailDTO implements Serializable {

    private String destinatario;
    private String corpo;
    private String assunto;
}
