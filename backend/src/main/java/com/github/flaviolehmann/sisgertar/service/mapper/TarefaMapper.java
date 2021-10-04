package com.github.flaviolehmann.sisgertar.service.mapper;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.domain.Usuario;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;
import java.util.Optional;


@Mapper(uses = { UsuarioMapper.class })
public interface TarefaMapper extends EntityMapper<Tarefa, TarefaDTO> {

    @Override
    @Mapping(source = "responsavel.id", target = "idResponsavel")
    TarefaDTO toDTO(Tarefa tarefa);

    @Override
    @Mapping(source = "idResponsavel", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    TarefaListDTO toListDTO(Tarefa tarefa);

    @AfterMapping
    default void verificarElementosNulos(@MappingTarget Tarefa tarefa) {
        tarefa.setResponsavel(
                Optional.ofNullable(tarefa.getResponsavel()).orElseGet(Usuario::new));
        if (Objects.isNull(tarefa.getResponsavel().getId())) {
            tarefa.setResponsavel(null);
        }
    }
}
