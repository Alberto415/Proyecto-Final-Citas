package com.uam.citas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "detallemedica")
public class DetalleMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleMedica")
    private Long idDetalleMedica;

    @Column(name = "motivoConsulta")
    private String motivoConsulta;

    @Column(name = "alergias")
    private String alergias;

    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    @JsonIgnoreProperties("detalleMedica")
    private CitaEntity cita;

    public DetalleMedicaEntity() {}

    public Long getIdDetalleMedica() { return idDetalleMedica; }
    public void setIdDetalleMedica(Long idDetalleMedica) { this.idDetalleMedica = idDetalleMedica; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public CitaEntity getCita() { return cita; }
    public void setCita(CitaEntity cita) { this.cita = cita; }
}