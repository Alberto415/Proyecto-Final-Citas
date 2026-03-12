package com.uam.citas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Expediente")
public class ExpedienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExpediante")
    private Long idExpediente;

    @Column(name = "fechaCreacion")
    private LocalDate fechaDeCreacion;

    @Column(name = "historialNotas", columnDefinition = "TEXT")
    private String historialNotas;

    @OneToOne(mappedBy = "expediente")
    @JsonIgnoreProperties("expediente")
    private UsuarioEntity usuario;

    public ExpedienteEntity() {}

    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
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