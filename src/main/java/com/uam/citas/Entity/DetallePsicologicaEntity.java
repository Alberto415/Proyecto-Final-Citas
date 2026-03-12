package com.uam.citas.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DetallePsicologica")
public class DetallePsicologicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetallePsicologica")
    private Long idDetallePsicologica;

    @Column(name = "motivoConsulta", columnDefinition = "TEXT")
    private String motivoConsulta;

    @Column(name = "diagnostico", columnDefinition = "TEXT")
    private String diagnostico;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @OneToOne
    @JoinColumn(name = "Cita_idCita", referencedColumnName = "idCita")
    private CitaEntity cita;

    public DetallePsicologicaEntity() {
    }

    public Long getIdDetallePsicologica() {
        return idDetallePsicologica;
    }

    public void setIdDetallePsicologica(Long idDetallePsicologica) {
        this.idDetallePsicologica = idDetallePsicologica;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public CitaEntity getCita() {
        return cita;
    }

    public void setCita(CitaEntity cita) {
        this.cita = cita;
    }
}