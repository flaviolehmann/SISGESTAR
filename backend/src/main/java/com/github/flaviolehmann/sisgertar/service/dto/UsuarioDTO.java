package com.github.flaviolehmann.sisgertar.service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String hash;
}
