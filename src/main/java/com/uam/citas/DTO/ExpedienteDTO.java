package com.uam.citas.DTO;

import java.util.Date;

public class ExpedienteDTO {
    private int idExpediente;
    private Date fechaDeCreacion;
    private String historialNotas;

    public int getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getHistorialNotas() {
        return historialNotas;
    }

    public void setHistorialNotas(String historialNotas) {
        this.historialNotas = historialNotas;
    }
}
