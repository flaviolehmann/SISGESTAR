package com.github.flaviolehmann.sisgertar.web.rest;

import com.github.flaviolehmann.sisgertar.service.UsuarioService;
import com.github.flaviolehmann.sisgertar.service.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }
}
