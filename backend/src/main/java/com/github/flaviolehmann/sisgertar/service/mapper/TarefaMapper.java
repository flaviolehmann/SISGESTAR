package com.github.flaviolehmann.sisgertar.service.mapper;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = { UsuarioMapper.class })
public interface TarefaMapper extends EntityMapper<Tarefa, TarefaDTO> {

    @Override
    @Mapping(source = "responsavel.id", target = "idResponsavel")
    TarefaDTO toDTO(Tarefa tarefa);

    @Override
    @Mapping(source = "idResponsavel", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    TarefaListDTO toListDTO(Tarefa tarefa);
}
