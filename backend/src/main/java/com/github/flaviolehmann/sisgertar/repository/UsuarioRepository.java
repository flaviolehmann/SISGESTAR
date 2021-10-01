package com.github.flaviolehmann.sisgertar.repository;


import com.github.flaviolehmann.sisgertar.domain.Tarefa;
import com.github.flaviolehmann.sisgertar.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
