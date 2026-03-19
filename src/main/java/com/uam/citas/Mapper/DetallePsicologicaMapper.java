package com.uam.citas.Mapper;

import com.uam.citas.DTO.DetallePsicologicaDTO;
import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Entity.DetallePsicologicaEntity;
import org.springframework.stereotype.Component;

@Component
public class DetallePsicologicaMapper {

    public DetallePsicologicaDTO toDTO(DetallePsicologicaEntity entity) {
        DetallePsicologicaDTO dto = new DetallePsicologicaDTO();
        dto.setIdDetallePsicologica(entity.getIdDetallePsicologica());
        dto.setDescripcionEmocional(entity.getDescripcionEmocional());
        dto.setMotivoConsulta(entity.getMotivoConsulta());

        if (entity.getCita() != null) {
            dto.setCitaIdCita(entity.getCita().getIdCita());
        }

        return dto;
    }

    public DetallePsicologicaEntity toEntity(DetallePsicologicaDTO dto) {
        DetallePsicologicaEntity entity = new DetallePsicologicaEntity();
        entity.setIdDetallePsicologica(dto.getIdDetallePsicologica());
        entity.setDescripcionEmocional(dto.getDescripcionEmocional());
        entity.setMotivoConsulta(dto.getMotivoConsulta());

        if (dto.getCitaIdCita() != null) {
            CitaEntity cita = new CitaEntity();
            cita.setIdCita(dto.getCitaIdCita());
            entity.setCita(cita);
        }

        return entity;
    }
}