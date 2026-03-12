package com.uam.citas.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DetalleNutricion")
public class DetalleNutricionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleNutricion")
    private Long idDetalleNutricion;

    @Column(name = "pesoActual")
    private Float pesoActual;

    @Column(name = "estatura")
    private Float estatura;

    @Column(name = "objetivo", columnDefinition = "TEXT")
    private String objetivo;


    @OneToOne
    @JoinColumn(name = "Cita_idCita", referencedColumnName = "idCita")
    private CitaEntity cita;

    public DetalleNutricionEntity() {
    }

    public Long getIdDetalleNutricion() {
        return idDetalleNutricion;
    }

    public void setIdDetalleNutricion(Long idDetalleNutricion) {
        this.idDetalleNutricion = idDetalleNutricion;
    }

    public Float getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(Float pesoActual) {
        this.pesoActual = pesoActual;
    }

    public Float getEstatura() {
        return estatura;
    }

    public void setEstatura(Float estatura) {
        this.estatura = estatura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public CitaEntity getCita() {
        return cita;
    }

    public void setCita(CitaEntity cita) {
        this.cita = cita;
    }
}