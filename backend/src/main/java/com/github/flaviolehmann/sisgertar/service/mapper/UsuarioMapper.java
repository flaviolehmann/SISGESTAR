package com.github.flaviolehmann.sisgertar.service.mapper;

import com.github.flaviolehmann.sisgertar.domain.Usuario;
import com.github.flaviolehmann.sisgertar.service.dto.UsuarioDTO;
import org.mapstruct.Mapper;


@Mapper
public interface UsuarioMapper extends EntityMapper<Usuario, UsuarioDTO> {
}
