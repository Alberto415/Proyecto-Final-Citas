package com.uam.citas.DTO;

public class DetallePsicologicaDTO {
    private Long idDetallePsicologica;
    private String descripcionEmocional;
    private String motivoConsulta; // corregido el typo
    private Long citaIdCita;

    public Long getIdDetallePsicologica() { return idDetallePsicologica; }
    public void setIdDetallePsicologica(Long idDetallePsicologica) { this.idDetallePsicologica = idDetallePsicologica; }

    public String getDescripcionEmocional() { return descripcionEmocional; }
    public void setDescripcionEmocional(String descripcionEmocional) { this.descripcionEmocional = descripcionEmocional; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public Long getCitaIdCita() { return citaIdCita; }
    public void setCitaIdCita(Long citaIdCita) { this.citaIdCita = citaIdCita; }
}