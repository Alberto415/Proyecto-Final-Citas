package com.uam.citas.Service;

import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Entity.UsuarioEntity;
import com.uam.citas.Repository.CitaRepository;
import com.uam.citas.Repository.DetallePsicologicaRepository;
import com.uam.citas.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private DetallePsicologicaRepository detallePsicologicaRepository;

    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity guardar(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioEntity> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public void eliminar(Long id) {
        List<CitaEntity> citas = citaRepository.findByUsuario_IdUsuario(id);
        for (CitaEntity cita : citas) {
            detallePsicologicaRepository.deleteByCita_IdCita(cita.getIdCita());
        }

        citaRepository.deleteAll(citas);

        usuarioRepository.deleteById(id);
    }
}