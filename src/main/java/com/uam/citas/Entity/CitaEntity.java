package com.uam.citas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCita")
    private Long idCita;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario")
    @JsonIgnoreProperties({"expediente", "citas"})
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "Especialista_idEspecialista")
    @JsonIgnoreProperties("citas")
    private EspecialistaEntity especialista;

    @ManyToOne
    @JoinColumn(name = "Horario_idhorario")
    @JsonIgnoreProperties("citas")
    private HorarioEntity horario;

    public CitaEntity() {
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

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

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public EspecialistaEntity getEspecialista() {
        return especialista;
    }

    public void setEspecialista(EspecialistaEntity especialista) {
        this.especialista = especialista;
    }

    public HorarioEntity getHorario() {
        return horario;
    }

    public void setHorario(HorarioEntity horario) {
        this.horario = horario;
    }
}