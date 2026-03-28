package com.uam.citas.DTO;

public class PeliculaDTO {

    private String titulo;
    private String sinopsis;
    private String fechaEstreno;
    private double popularidad;
    private String urlCartel;

    public PeliculaDTO() {}

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getSinopsis() { return sinopsis; }
    public void setSinopsis(String sinopsis) { this.sinopsis = sinopsis; }

    public String getFechaEstreno() { return fechaEstreno; }
    public void setFechaEstreno(String fechaEstreno) { this.fechaEstreno = fechaEstreno; }

    public double getPopularidad() { return popularidad; }
    public void setPopularidad(double popularidad) { this.popularidad = popularidad; }

    public String getUrlCartel() { return urlCartel; }
    public void setUrlCartel(String urlCartel) { this.urlCartel = urlCartel; }
}