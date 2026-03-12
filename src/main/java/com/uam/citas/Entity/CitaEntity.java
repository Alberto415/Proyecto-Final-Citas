package com.uam.citas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCita")
    private Long idCita;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    @JsonIgnoreProperties({"expediente", "citas"})
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "Especialista_idEspecialista", nullable = false)
    @JsonIgnoreProperties("citas")
    private EspecialistaEntity especialista;

    @ManyToOne
    @JoinColumn(name = "horario_idhorario", nullable = false) // 👈 corregido: minúscula
    @JsonIgnoreProperties("citas")
    private HorarioEntity horario;

    public CitaEntity() {}

    // Getters y setters
    public Long getIdCita() { return idCita; }
    public void setIdCita(Long idCita) { this.idCita = idCita; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public UsuarioEntity getUsuario() { return usuario; }
    public void setUsuario(UsuarioEntity usuario) { this.usuario = usuario; }

    public EspecialistaEntity getEspecialista() { return especialista; }
    public void setEspecialista(EspecialistaEntity especialista) { this.especialista = especialista; }

    public HorarioEntity getHorario() { return horario; }
    public void setHorario(HorarioEntity horario) { this.horario = horario; }
}