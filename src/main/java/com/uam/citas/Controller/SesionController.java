package com.uam.citas.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sesion")
public class SesionController {
    @GetMapping("/yo")
    public ResponseEntity<Map<String, Object>> yo(Authentication auth) {
        boolean esAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return ResponseEntity.ok(Map.of(
                "usuario", auth.getName(),
                "esAdmin", esAdmin
        ));
    }
}
