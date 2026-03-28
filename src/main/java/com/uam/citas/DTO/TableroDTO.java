package com.uam.citas.DTO;

import java.util.List;

public class TableroDTO {
    private String mensaje;
    private List<ClimaDTO> pronostico;
    private PaisDTO pais;
    private List<PeliculaDTO> peliculas;

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public List<ClimaDTO> getPronostico() { return pronostico; }
    public void setPronostico(List<ClimaDTO> pronostico) { this.pronostico = pronostico; }

    public PaisDTO getPais() { return pais; }
    public void setPais(PaisDTO pais) { this.pais = pais; }

    public List<PeliculaDTO> getPeliculas() { return peliculas; }
    public void setPeliculas(List<PeliculaDTO> peliculas) { this.peliculas = peliculas; }
}