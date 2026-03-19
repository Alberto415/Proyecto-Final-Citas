package com.uam.citas.Mapper;

import com.uam.citas.DTO.DetalleMedicaDTO;
import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Entity.DetalleMedicaEntity;
import org.springframework.stereotype.Component;

@Component
public class DetalleMedicaMapper {

    public DetalleMedicaDTO toDTO(DetalleMedicaEntity entity) {
        DetalleMedicaDTO dto = new DetalleMedicaDTO();
        dto.setIdDetalleMedica(entity.getIdDetalleMedica());
        dto.setMotivoConsulta(entity.getMotivoConsulta());
        dto.setAlergias(entity.getAlergias());

        if (entity.getCita() != null) {
            dto.setCitaIdCita(entity.getCita().getIdCita());
        }

        return dto;
    }

    public DetalleMedicaEntity toEntity(DetalleMedicaDTO dto) {
        DetalleMedicaEntity entity = new DetalleMedicaEntity();
        entity.setIdDetalleMedica(dto.getIdDetalleMedica());
        entity.setMotivoConsulta(dto.getMotivoConsulta());
        entity.setAlergias(dto.getAlergias());

        if (dto.getCitaIdCita() != null) {
            CitaEntity cita = new CitaEntity();
            cita.setIdCita(dto.getCitaIdCita());
            entity.setCita(cita);
        }

        return entity;
    }
}