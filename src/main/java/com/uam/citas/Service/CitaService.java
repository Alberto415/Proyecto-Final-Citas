package com.uam.citas.Service;

import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    public List<CitaEntity> listarTodas() { return citaRepository.findAll(); }
    public Optional<CitaEntity> buscarPorId(Long id) { return citaRepository.findById(id); }
    public CitaEntity guardar(CitaEntity cita) { return citaRepository.save(cita); }
    public void eliminar(Long id) { citaRepository.deleteById(id); }
}
