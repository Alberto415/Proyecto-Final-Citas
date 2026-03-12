package com.uam.citas.Service;

import com.uam.citas.Entity.UsuarioEntity;
import com.uam.citas.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity guardar(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }


    public Optional<UsuarioEntity> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}