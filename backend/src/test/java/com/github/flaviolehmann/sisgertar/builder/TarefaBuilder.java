package com.github.flaviolehmann.sisgertar.builder;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.repository.TarefaRepository;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaDTO;
import com.github.flaviolehmann.sisgertar.service.mapper.TarefaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarefaBuilder {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public TarefaDTO createTarefaDTO() {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setNome("Título Teste");
        tarefaDTO.setDescricao("Descrição Teste");
        return tarefaDTO;
    }

    public TarefaDTO persistirTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefa = tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(tarefa);
    }
}
