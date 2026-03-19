package com.uam.citas.Controller;

import com.uam.citas.DTO.EspecialistaDTO;
import com.uam.citas.Service.EspecialistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialistas")
public class EspecialistaController {

    private final EspecialistaService service;

    public EspecialistaController(EspecialistaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EspecialistaDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialistaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<EspecialistaDTO> create(@RequestBody EspecialistaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialistaDTO> update(@PathVariable Long id, @RequestBody EspecialistaDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}