package com.uam.citas.Service;

import com.uam.citas.DTO.UsuarioDTO;
import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Entity.UsuarioEntity;
import com.uam.citas.Mapper.UsuarioMapper;
import com.uam.citas.Repository.CitaRepository;
import com.uam.citas.Repository.DetallePsicologicaRepository;
import com.uam.citas.Repository.ExpedienteRepository;
import com.uam.citas.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final CitaRepository citaRepository;
    private final DetallePsicologicaRepository detallePsicologicaRepository;
    private final ExpedienteRepository expedienteRepository;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper,
                          CitaRepository citaRepository,
                          DetallePsicologicaRepository detallePsicologicaRepository,
                          ExpedienteRepository expedienteRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.citaRepository = citaRepository;
        this.detallePsicologicaRepository = detallePsicologicaRepository;
        this.expedienteRepository = expedienteRepository;
    }

    public List<UsuarioDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public UsuarioDTO create(UsuarioDTO dto) {
        UsuarioEntity entity = mapper.toEntity(dto);

        if (dto.getIdExpediente() != null) {
            entity.setExpediente(expedienteRepository.getReferenceById(dto.getIdExpediente()));
        }

        return mapper.toDTO(repository.save(entity));
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        UsuarioEntity entity = mapper.toEntity(dto);
        entity.setIdUsuario(id);

        if (dto.getIdExpediente() != null) {
            entity.setExpediente(expedienteRepository.getReferenceById(dto.getIdExpediente()));
        }

        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        List<CitaEntity> citas = citaRepository.findByUsuario_IdUsuario(id);
        for (CitaEntity cita : citas) {
            detallePsicologicaRepository.deleteByCita_IdCita(cita.getIdCita());
        }
        citaRepository.deleteAll(citas);
        repository.deleteById(id);
    }
}