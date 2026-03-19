package com.uam.citas.Service;

import com.uam.citas.DTO.DetallePsicologicaDTO;
import com.uam.citas.Entity.DetallePsicologicaEntity;
import com.uam.citas.Mapper.DetallePsicologicaMapper;
import com.uam.citas.Repository.DetallePsicologicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePsicologicaService {

    private final DetallePsicologicaRepository repository;
    private final DetallePsicologicaMapper mapper;

    public DetallePsicologicaService(DetallePsicologicaRepository repository, DetallePsicologicaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DetallePsicologicaDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public DetallePsicologicaDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("DetallePsicologica no encontrado con id: " + id));
    }

    public DetallePsicologicaDTO create(DetallePsicologicaDTO dto) {
        DetallePsicologicaEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public DetallePsicologicaDTO update(Long id, DetallePsicologicaDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetallePsicologica no encontrado con id: " + id));
        DetallePsicologicaEntity entity = mapper.toEntity(dto);
        entity.setIdDetallePsicologica(id);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetallePsicologica no encontrado con id: " + id));
        repository.deleteById(id);
    }
}