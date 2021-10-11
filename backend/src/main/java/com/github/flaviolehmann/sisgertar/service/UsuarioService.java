package com.github.flaviolehmann.sisgertar.service;


import com.github.flaviolehmann.sisgertar.domain.Usuario;
import com.github.flaviolehmann.sisgertar.repository.UsuarioRepository;
import com.github.flaviolehmann.sisgertar.service.dto.UsuarioDTO;
import com.github.flaviolehmann.sisgertar.service.error.UsuarioNaoEncontradaException;
import com.github.flaviolehmann.sisgertar.service.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public UsuarioDTO obterPorId(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(UsuarioNaoEncontradaException::new);
        return usuarioMapper.toDTO(usuario);
    }

    public Optional<UsuarioDTO> obterPorHash(String hash) {
        return usuarioRepository.findByHash(hash).map(usuarioMapper::toDTO);
    }

    public List<UsuarioDTO> findAll() {
      List<Usuario> usuarios = usuarioRepository.findAll();
      return usuarios.stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }
}
