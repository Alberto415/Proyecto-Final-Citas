package com.uam.citas.Service;

import com.uam.citas.DTO.DetalleMedicaDTO;
import com.uam.citas.Entity.DetalleMedicaEntity;
import com.uam.citas.Mapper.DetalleMedicaMapper;
import com.uam.citas.Repository.DetalleMedicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleMedicaService {

    private final DetalleMedicaRepository repository;
    private final DetalleMedicaMapper mapper;

    public DetalleMedicaService(DetalleMedicaRepository repository, DetalleMedicaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DetalleMedicaDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public DetalleMedicaDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("DetalleMedica no encontrado con id: " + id));
    }

    public DetalleMedicaDTO create(DetalleMedicaDTO dto) {
        DetalleMedicaEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public DetalleMedicaDTO update(Long id, DetalleMedicaDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleMedica no encontrado con id: " + id));
        DetalleMedicaEntity entity = mapper.toEntity(dto);
        entity.setIdDetalleMedica(id);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleMedica no encontrado con id: " + id));
        repository.deleteById(id);
    }
}