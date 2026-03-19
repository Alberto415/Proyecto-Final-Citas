package com.uam.citas.DTO;

public class DetalleMedicaDTO {
    private Long idDetalleMedica;
    private String motivoConsulta;
    private String alergias;
    private Long citaIdCita;

    public Long getIdDetalleMedica() { return idDetalleMedica; }
    public void setIdDetalleMedica(Long idDetalleMedica) { this.idDetalleMedica = idDetalleMedica; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public Long getCitaIdCita() { return citaIdCita; }
    public void setCitaIdCita(Long citaIdCita) { this.citaIdCita = citaIdCita; }
}