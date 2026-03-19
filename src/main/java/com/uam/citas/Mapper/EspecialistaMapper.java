package com.uam.citas.Mapper;

import com.uam.citas.DTO.EspecialistaDTO;
import com.uam.citas.Entity.EspecialistaEntity;
import org.springframework.stereotype.Component;

@Component
public class EspecialistaMapper {

    public EspecialistaDTO toDTO(EspecialistaEntity entity) {
        EspecialistaDTO dto = new EspecialistaDTO();
        dto.setIdEspecialista(entity.getIdEspecialista());
        dto.setNombre(entity.getNombre());
        dto.setCorreo(entity.getCorreo());
        dto.setTelefono(entity.getTelefono());
        dto.setArea(entity.getArea());
        return dto;
    }

    public EspecialistaEntity toEntity(EspecialistaDTO dto) {
        EspecialistaEntity entity = new EspecialistaEntity();
        entity.setIdEspecialista(dto.getIdEspecialista());
        entity.setNombre(dto.getNombre());
        entity.setCorreo(dto.getCorreo());
        entity.setTelefono(dto.getTelefono());
        entity.setArea(dto.getArea());
        return entity;
    }
}