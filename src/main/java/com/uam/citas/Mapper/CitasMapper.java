package com.uam.citas.Mapper;

import com.uam.citas.DTO.CitaDTO;
import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Entity.EspecialistaEntity;
import com.uam.citas.Entity.HorarioEntity;
import com.uam.citas.Entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class CitasMapper {

    public CitaDTO toDTO(CitaEntity entity) {
        CitaDTO dto = new CitaDTO();
        dto.setIdCita(entity.getIdCita());
        dto.setFecha(entity.getFecha());
        dto.setHora(entity.getHora());
        dto.setEstado(entity.getEstado());

        if (entity.getUsuario() != null) {
            dto.setUsuarioIdUsuario(entity.getUsuario().getIdUsuario());
        }
        if (entity.getEspecialista() != null) {
            dto.setEspecialistaIdEspecialista(entity.getEspecialista().getIdEspecialista());
        }
        if (entity.getHorario() != null) {
            dto.setHorarioIdhorario(entity.getHorario().getIdhorario());
        }

        return dto;
    }

    public CitaEntity toEntity(CitaDTO dto) {
        CitaEntity entity = new CitaEntity();
        entity.setIdCita(dto.getIdCita());
        entity.setFecha(dto.getFecha());
        entity.setHora(dto.getHora());
        entity.setEstado(dto.getEstado());

        if (dto.getUsuarioIdUsuario() != null) {
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setIdUsuario(dto.getUsuarioIdUsuario());
            entity.setUsuario(usuario);
        }
        if (dto.getEspecialistaIdEspecialista() != null) {
            EspecialistaEntity especialista = new EspecialistaEntity();
            especialista.setIdEspecialista(dto.getEspecialistaIdEspecialista());
            entity.setEspecialista(especialista);
        }
        if (dto.getHorarioIdhorario() != null) {
            HorarioEntity horario = new HorarioEntity();
            horario.setIdhorario(dto.getHorarioIdhorario());
            entity.setHorario(horario);
        }

        return entity;
    }
}