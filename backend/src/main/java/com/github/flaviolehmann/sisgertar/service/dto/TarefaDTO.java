package com.github.flaviolehmann.sisgertar.service.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TarefaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Long idResponsavel;
    private Long idStatus;
    private String descricaoStatus;
    private List<UsuarioDTO> acompanhadores = new ArrayList<>();
}
