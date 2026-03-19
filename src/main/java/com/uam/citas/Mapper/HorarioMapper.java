package com.uam.citas.Mapper;

import com.uam.citas.DTO.HorarioDTO;
import com.uam.citas.Entity.HorarioEntity;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    public HorarioDTO toDTO(HorarioEntity entity) {
        HorarioDTO dto = new HorarioDTO();
        dto.setIdhorario(entity.getIdhorario());
        dto.setDuracion(entity.getDuracion());
        dto.setDisponible(entity.getDisponible());
        dto.setEspecialista(entity.getEspecialista());
        return dto;
    }

    public HorarioEntity toEntity(HorarioDTO dto) {
        HorarioEntity entity = new HorarioEntity();
        entity.setIdhorario(dto.getIdhorario());
        entity.setDuracion(dto.getDuracion());
        entity.setDisponible(dto.getDisponible());
        entity.setEspecialista(dto.getEspecialista());
        return entity;
    }
}