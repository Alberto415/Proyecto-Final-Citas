package com.uam.citas.Service;

import com.uam.citas.DTO.DetalleNutricionDTO;
import com.uam.citas.Entity.DetalleNutricionEntity;
import com.uam.citas.Mapper.DetalleNutricionMapper;
import com.uam.citas.Repository.DetalleNutricionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleNutricionService {

    private final DetalleNutricionRepository repository;
    private final DetalleNutricionMapper mapper;

    public DetalleNutricionService(DetalleNutricionRepository repository, DetalleNutricionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DetalleNutricionDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public DetalleNutricionDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("DetalleNutricion no encontrado con id: " + id));
    }

    public DetalleNutricionDTO create(DetalleNutricionDTO dto) {
        DetalleNutricionEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public DetalleNutricionDTO update(Long id, DetalleNutricionDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleNutricion no encontrado con id: " + id));
        DetalleNutricionEntity entity = mapper.toEntity(dto);
        entity.setIdDetalleNutricion(id);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleNutricion no encontrado con id: " + id));
        repository.deleteById(id);
    }
}