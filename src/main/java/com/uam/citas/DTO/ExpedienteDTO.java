package com.uam.citas.DTO;

import java.time.LocalDate;

public class ExpedienteDTO {
    private Long idExpediente;
    private LocalDate fechaDeCreacion;
    private String historialNotas;

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
}