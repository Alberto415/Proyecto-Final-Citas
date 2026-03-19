package com.uam.citas.Service;

import com.uam.citas.DTO.ExpedienteDTO;
import com.uam.citas.Entity.ExpedienteEntity;
import com.uam.citas.Mapper.ExpedienteMapper;
import com.uam.citas.Repository.ExpedienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpedienteService {

    private final ExpedienteRepository repository;
    private final ExpedienteMapper mapper;

    public ExpedienteService(ExpedienteRepository repository, ExpedienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ExpedienteDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public ExpedienteDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado con id: " + id));
    }

    public ExpedienteDTO create(ExpedienteDTO dto) {
        ExpedienteEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public ExpedienteDTO update(Long id, ExpedienteDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado con id: " + id));
        ExpedienteEntity entity = mapper.toEntity(dto);
        entity.setIdExpediante(id);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expediente no encontrado con id: " + id));
        repository.deleteById(id);
    }
}