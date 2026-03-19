package com.uam.citas.Mapper;

import com.uam.citas.DTO.DetalleNutricionDTO;
import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Entity.DetalleNutricionEntity;
import org.springframework.stereotype.Component;

@Component
public class DetalleNutricionMapper {

    public DetalleNutricionDTO toDTO(DetalleNutricionEntity entity) {
        DetalleNutricionDTO dto = new DetalleNutricionDTO();
        dto.setIdDetalleNutricion(entity.getIdDetalleNutricion());
        dto.setPesoActual(entity.getPesoActual());
        dto.setEstatura(entity.getEstatura());
        dto.setObjetivo(entity.getObjetivo());

        if (entity.getCita() != null) {
            dto.setCitaIdCita(entity.getCita().getIdCita());
        }

        return dto;
    }

    public DetalleNutricionEntity toEntity(DetalleNutricionDTO dto) {
        DetalleNutricionEntity entity = new DetalleNutricionEntity();
        entity.setIdDetalleNutricion(dto.getIdDetalleNutricion());
        entity.setPesoActual(dto.getPesoActual());
        entity.setEstatura(dto.getEstatura());
        entity.setObjetivo(dto.getObjetivo());

        if (dto.getCitaIdCita() != null) {
            CitaEntity cita = new CitaEntity();
            cita.setIdCita(dto.getCitaIdCita());
            entity.setCita(cita);
        }

        return entity;
    }
}