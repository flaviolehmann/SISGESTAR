package com.github.flaviolehmann.sisgertar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaResource {

    List<Tarefa> tarefas = new ArrayList<>();

    @GetMapping
    public List<Tarefa> recuperarTodos() {
        return tarefas;
    }

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa novaTarefa) {
        tarefas.add(novaTarefa);
        return novaTarefa;
    }
}
