package com.uam.citas.DTO;

public class DetalleNutricionDTO {
    private Long idDetalleNutricion;
    private Float pesoActual;
    private Float estatura;
    private String objetivo;
    private Long citaIdCita;

    public Long getIdDetalleNutricion() { return idDetalleNutricion; }
    public void setIdDetalleNutricion(Long idDetalleNutricion) { this.idDetalleNutricion = idDetalleNutricion; }

    public Float getPesoActual() { return pesoActual; }
    public void setPesoActual(Float pesoActual) { this.pesoActual = pesoActual; }

    public Float getEstatura() { return estatura; }
    public void setEstatura(Float estatura) { this.estatura = estatura; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public Long getCitaIdCita() { return citaIdCita; }
    public void setCitaIdCita(Long citaIdCita) { this.citaIdCita = citaIdCita; }
}