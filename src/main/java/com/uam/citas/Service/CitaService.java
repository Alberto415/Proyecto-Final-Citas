package com.uam.citas.Service;

import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Repository.CitaRepository;
import com.uam.citas.Repository.EspecialistaRepository;
import com.uam.citas.Repository.HorarioRepository;
import com.uam.citas.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EspecialistaRepository EspecialistaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    public CitaEntity guardar(CitaEntity cita) {

        if (cita.getUsuario() != null && cita.getUsuario().getIdUsuario() != null) {
            cita.setUsuario(
                    usuarioRepository.getReferenceById(cita.getUsuario().getIdUsuario())
            );
        }

        if (cita.getEspecialista() != null && cita.getEspecialista().getIdEspecialista() != null) {
            cita.setEspecialista(
                    EspecialistaRepository.getReferenceById(cita.getEspecialista().getIdEspecialista())
            );
        }

        if (cita.getHorario() != null && cita.getHorario().getIdhorario() != null) {
            cita.setHorario(
                    horarioRepository.getReferenceById(cita.getHorario().getIdhorario())
            );
        }

        return citaRepository.save(cita);
    }

    public CitaEntity crearCita(CitaEntity cita) {
        return guardar(cita);
    }

    public List<CitaEntity> listarTodas() {
        return citaRepository.findAll();
    }

    public Optional<CitaEntity> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}