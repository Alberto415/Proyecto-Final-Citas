package com.uam.citas.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private Integer usuarioIdUsuario;
    private Integer especialistaIdEspecialista;
    private Integer horarioIdhorario;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Integer usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public Integer getEspecialistaIdEspecialista() {
        return especialistaIdEspecialista;
    }

    public void setEspecialistaIdEspecialista(Integer especialistaIdEspecialista) {
        this.especialistaIdEspecialista = especialistaIdEspecialista;
    }

    public Integer getHorarioIdhorario() {
        return horarioIdhorario;
    }

    public void setHorarioIdhorario(Integer horarioIdhorario) {
        this.horarioIdhorario = horarioIdhorario;
    }
}