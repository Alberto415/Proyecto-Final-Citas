package com.uam.citas.DTO;

public class ClimaDTO {
    private String fecha;
    private double temperatura;
    private int humedad;
    private String descripcion;

    public ClimaDTO(String fecha, double temperatura, int humedad, String descripcion) {
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.descripcion = descripcion;
    }

    public String getFecha() { return fecha; }
    public double getTemperatura() { return temperatura; }
    public int getHumedad() { return humedad; }
    public String getDescripcion() { return descripcion; }
}