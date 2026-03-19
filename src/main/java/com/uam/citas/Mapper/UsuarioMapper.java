package com.uam.citas.Mapper;

import com.uam.citas.DTO.UsuarioDTO;
import com.uam.citas.Entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(UsuarioEntity entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setMatricula(entity.getMatricula());
        dto.setNombre(entity.getNombre());
        dto.setCorreo(entity.getCorreo());
        dto.setTelefono(entity.getTelefono());
        dto.setTipoUsuario(entity.getTipoUsuario());

        if (entity.getExpediente() != null) {
            dto.setIdExpediente(entity.getExpediente().getIdExpediante());
        }

        return dto;
    }

    public UsuarioEntity toEntity(UsuarioDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setMatricula(dto.getMatricula());
        entity.setNombre(dto.getNombre());
        entity.setCorreo(dto.getCorreo());
        entity.setTelefono(dto.getTelefono());
        entity.setTipoUsuario(dto.getTipoUsuario());
        // El expediente se asigna en el Service con getReferenceById
        return entity;
    }
}