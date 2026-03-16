package com.uam.citas.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DetallePsicologica")
public class DetallePsicologicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetallePsicologica")
    private Long idDetallePsicologica;

    @Column(name = "descripcionEmocional", columnDefinition = "TEXT")  // ✅ existe en BD
    private String descripcionEmocional;

    @Column(name = "motivoConsulta", columnDefinition = "TEXT")  // ✅ existe en BD
    private String motivoConsulta;

    @OneToOne
    @JoinColumn(name = "cita_id", referencedColumnName = "idCita")  // ✅ corregido
    private CitaEntity cita;

    public DetallePsicologicaEntity() {}

    public Long getIdDetallePsicologica() {
        return idDetallePsicologica;
    }

    public void setIdDetallePsicologica(Long idDetallePsicologica) {
        this.idDetallePsicologica = idDetallePsicologica;
    }

    public String getDescripcionEmocional() {
        return descripcionEmocional;
    }

    public void setDescripcionEmocional(String descripcionEmocional) {
        this.descripcionEmocional = descripcionEmocional;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public CitaEntity getCita() {
        return cita;
    }

    public void setCita(CitaEntity cita) {
        this.cita = cita;
    }
}