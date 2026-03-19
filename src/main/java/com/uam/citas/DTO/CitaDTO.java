package com.uam.citas.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO {
    private Long idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private Long usuarioIdUsuario;
    private Long especialistaIdEspecialista;
    private Long horarioIdhorario;

    public Long getIdCita() { return idCita; }
    public void setIdCita(Long idCita) { this.idCita = idCita; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getUsuarioIdUsuario() { return usuarioIdUsuario; }
    public void setUsuarioIdUsuario(Long usuarioIdUsuario) { this.usuarioIdUsuario = usuarioIdUsuario; }

    public Long getEspecialistaIdEspecialista() { return especialistaIdEspecialista; }
    public void setEspecialistaIdEspecialista(Long especialistaIdEspecialista) { this.especialistaIdEspecialista = especialistaIdEspecialista; }

    public Long getHorarioIdhorario() { return horarioIdhorario; }
    public void setHorarioIdhorario(Long horarioIdhorario) { this.horarioIdhorario = horarioIdhorario; }
}