package com.uam.citas.Service;

import com.uam.citas.DTO.HorarioDTO;
import com.uam.citas.Entity.HorarioEntity;
import com.uam.citas.Mapper.HorarioMapper;
import com.uam.citas.Repository.HorarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorarioService {

    private final HorarioRepository repository;
    private final HorarioMapper mapper;

    public HorarioService(HorarioRepository repository, HorarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<HorarioDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public HorarioDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con id: " + id));
    }

    public HorarioDTO create(HorarioDTO dto) {
        HorarioEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public HorarioDTO update(Long id, HorarioDTO dto) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con id: " + id));
        HorarioEntity entity = mapper.toEntity(dto);
        entity.setIdhorario(id);
        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con id: " + id));
        repository.deleteById(id);
    }
}