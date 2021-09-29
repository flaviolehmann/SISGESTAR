package com.github.flaviolehmann.sisgertar.service.mapper;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefaDTO toDTO(Tarefa tarefa);

    TarefaListDTO toListDTO(Tarefa tarefa);

    Tarefa toEntity(TarefaDTO tarefaDTO);
}
