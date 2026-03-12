package com.uam.citas.Controller;

import com.uam.citas.Entity.CitaEntity;
import com.uam.citas.Service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<CitaEntity> listar() {
        return citaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaEntity> buscar(@PathVariable Long id) {
        return citaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CitaEntity crear(@RequestBody CitaEntity cita) {
        return citaService.guardar(cita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaEntity> actualizar(@PathVariable Long id, @RequestBody CitaEntity detallesCita) {
        return citaService.buscarPorId(id).map(cita -> {
            cita.setFecha(detallesCita.getFecha());
            cita.setHora(detallesCita.getHora());
            cita.setEstado(detallesCita.getEstado());

            if (detallesCita.getUsuario() != null) cita.setUsuario(detallesCita.getUsuario());
            if (detallesCita.getEspecialista() != null) cita.setEspecialista(detallesCita.getEspecialista());

            CitaEntity actualizada = citaService.guardar(cita);
            return ResponseEntity.ok(actualizada);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}