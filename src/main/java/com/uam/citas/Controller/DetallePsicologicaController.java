package com.uam.citas.Controller;

import com.uam.citas.DTO.DetallePsicologicaDTO;
import com.uam.citas.Service.DetallePsicologicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-psicologica")
public class DetallePsicologicaController {

    private final DetallePsicologicaService service;

    public DetallePsicologicaController(DetallePsicologicaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DetallePsicologicaDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePsicologicaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<DetallePsicologicaDTO> create(@RequestBody DetallePsicologicaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePsicologicaDTO> update(@PathVariable Long id, @RequestBody DetallePsicologicaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}