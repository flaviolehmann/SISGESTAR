package com.github.flaviolehmann.sisgertar.web.rest;

import com.github.flaviolehmann.sisgertar.service.TarefaService;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaFilterDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaResource {

    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<Page<TarefaListDTO>> findAll(@ModelAttribute TarefaFilterDTO filterDTO, Pageable page) {
        return ResponseEntity.ok(tarefaService.findAll(filterDTO, page));
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

    @PatchMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizarStatus(@PathVariable("id") Long id,
                                                     @RequestBody TarefaDTO tarefa,
                                                     @RequestParam("hash") String hash) {
        tarefa.setId(id);
        return ResponseEntity.ok(tarefaService.atualizarStatus(tarefa, hash));
    }
}
