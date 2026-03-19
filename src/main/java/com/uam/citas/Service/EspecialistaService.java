package com.uam.citas.Service;

import com.uam.citas.DTO.EspecialistaDTO;
import com.uam.citas.Entity.EspecialistaEntity;
import com.uam.citas.Mapper.EspecialistaMapper;
import com.uam.citas.Repository.EspecialistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspecialistaService {

    private final EspecialistaRepository repository;
    private final EspecialistaMapper mapper;

    public EspecialistaService(EspecialistaRepository repository, EspecialistaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<EspecialistaDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public EspecialistaDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado con id: " + id));
    }

    public EspecialistaDTO create(EspecialistaDTO dto) {
        EspecialistaEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public EspecialistaDTO update(Long id, EspecialistaDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado con id: " + id));
        EspecialistaEntity entity = mapper.toEntity(dto);
        entity.setIdEspecialista(id);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado con id: " + id));
        repository.deleteById(id);
    }
}