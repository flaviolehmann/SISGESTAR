package com.github.flaviolehmann.sisgertar.repository;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaFilterDTO;
import com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query(
            "SELECT new com.github.flaviolehmann.sisgertar.service.dto.TarefaListDTO(" +
            "   tarefa.id, " +
            "   tarefa.nome " +
            ") FROM " +
            "   Tarefa tarefa " +
            "WHERE " +
            "   tarefa.nome LIKE %:#{#filtro.nome}%")
    Page<TarefaListDTO> filtrarTarefas(@Param("filtro") TarefaFilterDTO filterDTO, Pageable page);

}
