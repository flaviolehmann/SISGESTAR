package com.github.flaviolehmann.sisgertar.service;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.domain.enumarations.StatusTarefaEnum;
import com.github.flaviolehmann.sisgertar.repository.TarefaRepository;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO;
import com.github.flaviolehmann.sisgertar.service.error.TarefaNaoEncontradaException;
import com.github.flaviolehmann.sisgertar.service.error.UsuarioNaoAutorizadoException;
import com.github.flaviolehmann.sisgertar.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final UsuarioService usuarioService;

    public List<TarefaListDTO> findAll() {
        return tarefaRepository.findAll().stream()
                .map(tarefaMapper::toListDTO)
                .collect(Collectors.toList());
    }

    public TarefaDTO save(TarefaDTO tarefaDTO) {
        validarResponsavel(tarefaDTO);
        definirStatusInicial(tarefaDTO);
        Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
        tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(tarefa);
    }

    private void validarResponsavel(TarefaDTO tarefaDTO) {
        if (Objects.nonNull(tarefaDTO.getIdResponsavel())) {
            usuarioService.obterPorId(tarefaDTO.getIdResponsavel());
        }
    }

    private void definirStatusInicial(TarefaDTO tarefa) {
        tarefa.setIdStatus(StatusTarefaEnum.A_FAZER.getId());
    }

    public Optional<TarefaDTO> findById(Long id) {
        return tarefaRepository.findById(id).map(tarefaMapper::toDTO);
    }

    public void deleteById(Long id) {
        tarefaRepository.deleteById(id);
    }

    public TarefaDTO atualizarStatus(TarefaDTO tarefaDTO, String hash) {
        Tarefa tarefaEmBanco = tarefaRepository.findById(tarefaDTO.getId())
                .orElseThrow(TarefaNaoEncontradaException::new);
        validarResponsavel(tarefaEmBanco, hash);
        tarefaEmBanco.setIdStatus(tarefaDTO.getIdStatus());
        tarefaRepository.save(tarefaEmBanco);
        return tarefaMapper.toDTO(tarefaEmBanco);
    }

    private void validarResponsavel(Tarefa tarefa, String hash) {
        if (!tarefa.getResponsavel().getHash().equals(hash)) {
            throw new UsuarioNaoAutorizadoException();
        }
    }
}
