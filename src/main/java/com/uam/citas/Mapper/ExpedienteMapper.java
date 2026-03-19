package com.uam.citas.Mapper;

import com.uam.citas.DTO.ExpedienteDTO;
import com.uam.citas.Entity.ExpedienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ExpedienteMapper {

    public ExpedienteDTO toDTO(ExpedienteEntity entity) {
        ExpedienteDTO dto = new ExpedienteDTO();
        dto.setIdExpediente(entity.getIdExpediante());
        dto.setFechaDeCreacion(entity.getFechaDeCreacion());
        dto.setHistorialNotas(entity.getHistorialNotas());
        return dto;
    }

    public ExpedienteEntity toEntity(ExpedienteDTO dto) {
        ExpedienteEntity entity = new ExpedienteEntity();
        entity.setIdExpediante(dto.getIdExpediente());
        entity.setFechaDeCreacion(dto.getFechaDeCreacion());
        entity.setHistorialNotas(dto.getHistorialNotas());
        return entity;
    }
}