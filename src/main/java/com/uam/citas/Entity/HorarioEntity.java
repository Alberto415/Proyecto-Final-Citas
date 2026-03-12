package com.uam.citas.Entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Horario")
public class HorarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhorario")
    private Long idhorario;

    @Column(name = "duracion")
    private LocalTime duracion;

    @Column(name = "disponible")
    private Integer disponible;

    @Column(name = "especialista")
    private String especialista;

    public HorarioEntity() {
    }

    public Long getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(Long idhorario) {
        this.idhorario = idhorario;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public Integer getDisponible() {
        return disponible;
    }

    public void setDisponible(Integer disponible) {
        this.disponible = disponible;
    }

    public String getEspecialista() {
        return especialista;
    }

    public void setEspecialista(String especialista) {
        this.especialista = especialista;
    }
}