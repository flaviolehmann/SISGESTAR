package com.github.flaviolehmann.sisgertar.service;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.repository.TarefaRepository;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO;
import com.github.flaviolehmann.sisgertar.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaListDTO> findAll() {
        return tarefaRepository.findAll().stream()
                .map(tarefaMapper::toListDTO)
                .collect(Collectors.toList());
    }

    public TarefaDTO save(TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(tarefa);
    }

    public Optional<TarefaDTO> findById(Long id) {
        return tarefaRepository.findById(id).map(tarefaMapper::toDTO);
    }

    public void deleteById(Long id) {
        tarefaRepository.deleteById(id);
    }
}
