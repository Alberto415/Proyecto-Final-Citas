package com.uam.citas.Service;

import com.uam.citas.DTO.CitaDTO;
import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Mapper.CitasMapper;
import com.uam.citas.Repository.CitaRepository;
import com.uam.citas.Repository.EspecialistaRepository;
import com.uam.citas.Repository.HorarioRepository;
import com.uam.citas.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    private final CitaRepository repository;
    private final CitasMapper mapper;
    private final UsuarioRepository usuarioRepository;
    private final EspecialistaRepository especialistaRepository;
    private final HorarioRepository horarioRepository;

    public CitaService(CitaRepository repository, CitasMapper mapper,
                       UsuarioRepository usuarioRepository,
                       EspecialistaRepository especialistaRepository,
                       HorarioRepository horarioRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
        this.especialistaRepository = especialistaRepository;
        this.horarioRepository = horarioRepository;
    }

    public List<CitaDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public CitaDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }

    public CitaDTO create(CitaDTO dto) {
        CitaEntity entity = mapper.toEntity(dto);

        if (dto.getUsuarioIdUsuario() != null) {
            entity.setUsuario(usuarioRepository.getReferenceById(dto.getUsuarioIdUsuario()));
        }
        if (dto.getEspecialistaIdEspecialista() != null) {
            entity.setEspecialista(especialistaRepository.getReferenceById(dto.getEspecialistaIdEspecialista()));
        }
        if (dto.getHorarioIdhorario() != null) {
            entity.setHorario(horarioRepository.getReferenceById(dto.getHorarioIdhorario()));
        }

        return mapper.toDTO(repository.save(entity));
    }

    public CitaDTO update(Long id, CitaDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
        CitaEntity entity = mapper.toEntity(dto);
        entity.setIdCita(id);

        if (dto.getUsuarioIdUsuario() != null) {
            entity.setUsuario(usuarioRepository.getReferenceById(dto.getUsuarioIdUsuario()));
        }
        if (dto.getEspecialistaIdEspecialista() != null) {
            entity.setEspecialista(especialistaRepository.getReferenceById(dto.getEspecialistaIdEspecialista()));
        }
        if (dto.getHorarioIdhorario() != null) {
            entity.setHorario(horarioRepository.getReferenceById(dto.getHorarioIdhorario()));
        }

        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
        repository.deleteById(id);
    }
}