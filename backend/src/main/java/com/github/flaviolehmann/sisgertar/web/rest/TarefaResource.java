package com.github.flaviolehmann.sisgertar.web.rest;

import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.service.TarefaService;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaResource {

    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaListDTO>> findAll() {
        return ResponseEntity.ok(tarefaService.findAll());
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@RequestBody TarefaDTO tarefa) {
        return ResponseEntity.ok(tarefaService.save(tarefa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.of(tarefaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> update(@PathVariable("id") Long id,
                                         @RequestBody TarefaDTO tarefa) {
        tarefa.setId(id);
        return ResponseEntity.ok(tarefaService.save(tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        tarefaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
