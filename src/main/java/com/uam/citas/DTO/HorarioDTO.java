package com.uam.citas.DTO;

import java.time.LocalTime;

public class HorarioDTO {
    private Long idhorario;
    private LocalTime duracion;
    private Integer disponible;
    private String especialista;

    public Long getIdhorario() { return idhorario; }
    public void setIdhorario(Long idhorario) { this.idhorario = idhorario; }

    public LocalTime getDuracion() { return duracion; }
    public void setDuracion(LocalTime duracion) { this.duracion = duracion; }

    public Integer getDisponible() { return disponible; }
    public void setDisponible(Integer disponible) { this.disponible = disponible; }

    public String getEspecialista() { return especialista; }
    public void setEspecialista(String especialista) { this.especialista = especialista; }
}