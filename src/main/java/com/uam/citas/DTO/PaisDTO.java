package com.uam.citas.DTO;

public class PaisDTO {

    private String nombre;
    private String capital;
    private String region;
    private double area;
    private String urlBandera;

    public PaisDTO() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public String getUrlBandera() { return urlBandera; }
    public void setUrlBandera(String urlBandera) { this.urlBandera = urlBandera; }
}