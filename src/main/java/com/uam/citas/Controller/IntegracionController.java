package com.uam.citas.Controller;

import com.uam.citas.DTO.TableroDTO;
import com.uam.citas.Service.IntegracionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integracion")
public class IntegracionController {

    private final IntegracionService servicio;

    public IntegracionController(IntegracionService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/tablero")
    public ResponseEntity<TableroDTO> obtenerTablero(
            @RequestParam(defaultValue = "mexico") String pais) {
        return ResponseEntity.ok(servicio.obtenerTablero(pais));
    }
}