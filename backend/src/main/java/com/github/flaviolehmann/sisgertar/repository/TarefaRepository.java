package com.github.flaviolehmann.sisgertar.repository;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
