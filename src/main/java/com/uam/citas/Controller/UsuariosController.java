package com.uam.citas.Controller;

import com.uam.citas.Entity.UsuarioEntity;
import com.uam.citas.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioEntity> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioEntity crear(@RequestBody UsuarioEntity usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> actualizar(@PathVariable Long id, @RequestBody UsuarioEntity usuarioDetalles) {
        return usuarioService.buscarPorId(id).map(usuario -> {
            usuario.setMatricula(usuarioDetalles.getMatricula());
            usuario.setCorreo(usuarioDetalles.getCorreo());
            usuario.setTelefono(usuarioDetalles.getTelefono());
            usuario.setTipoUsuario(usuarioDetalles.getTipoUsuario());

            if (usuarioDetalles.getExpediente() != null) {
                usuario.setExpediente(usuarioDetalles.getExpediente());
            }

            UsuarioEntity actualizado = usuarioService.guardar(usuario);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}