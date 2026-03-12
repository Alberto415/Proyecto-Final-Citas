package com.uam.citas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expediente") // 👈 igual que en la BD, en minúsculas
public class ExpedienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExpediante")   // 👈 igual que en la BD (con “a”)
    private Long idExpediante;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaDeCreacion;

    @Column(name = "historialNotas", columnDefinition = "TEXT")
    private String historialNotas;

    @OneToOne(mappedBy = "expediente")
    @JsonIgnoreProperties("expediente")
    private UsuarioEntity usuario;

    public ExpedienteEntity() {}

    public Long getIdExpediante() {
        return idExpediante;
    }

    public void setIdExpediante(Long idExpediante) {
        this.idExpediante = idExpediante;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getHistorialNotas() {
        return historialNotas;
    }

    public void setHistorialNotas(String historialNotas) {
        this.historialNotas = historialNotas;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}