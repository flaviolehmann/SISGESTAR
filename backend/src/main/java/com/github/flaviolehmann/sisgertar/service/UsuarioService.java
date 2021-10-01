package com.github.flaviolehmann.sisgertar.service;


import com.github.flaviolehmann.sisgertar.domain.Usuario;
import com.github.flaviolehmann.sisgertar.repository.UsuarioRepository;
import com.github.flaviolehmann.sisgertar.service.dto.UsuarioDTO;
import com.github.flaviolehmann.sisgertar.service.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setHash(UUID.randomUUID().toString());
        usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }
}
