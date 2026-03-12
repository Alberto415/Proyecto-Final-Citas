package com.uam.citas.DTO;

public class DetalleNutricionDTO {
    private Integer idDetalleNutricion;
    private Float pesoActual;
    private Float estatura;
    private String objetivo;
    private Integer citaIdCita;
    private Integer citaUsuarioIdUsuario;
    private Integer citaUsuarioExpedienteIdExpediente;
    private Integer citaEspecialistaIdEspecialista;
    private Integer citaHorarioIdhorario;

    public Integer getIdDetalleNutricion() {
        return idDetalleNutricion;
    }

    public void setIdDetalleNutricion(Integer idDetalleNutricion) {
        this.idDetalleNutricion = idDetalleNutricion;
    }

    public Float getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(Float pesoActual) {
        this.pesoActual = pesoActual;
    }

    public Float getEstatura() {
        return estatura;
    }

    public void setEstatura(Float estatura) {
        this.estatura = estatura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getCitaIdCita() {
        return citaIdCita;
    }

    public void setCitaIdCita(Integer citaIdCita) {
        this.citaIdCita = citaIdCita;
    }

    public Integer getCitaUsuarioIdUsuario() {
        return citaUsuarioIdUsuario;
    }

    public void setCitaUsuarioIdUsuario(Integer citaUsuarioIdUsuario) {
        this.citaUsuarioIdUsuario = citaUsuarioIdUsuario;
    }

    public Integer getCitaUsuarioExpedienteIdExpediente() {
        return citaUsuarioExpedienteIdExpediente;
    }

    public void setCitaUsuarioExpedienteIdExpediente(Integer citaUsuarioExpedienteIdExpediente) {
        this.citaUsuarioExpedienteIdExpediente = citaUsuarioExpedienteIdExpediente;
    }

    public Integer getCitaEspecialistaIdEspecialista() {
        return citaEspecialistaIdEspecialista;
    }

    public void setCitaEspecialistaIdEspecialista(Integer citaEspecialistaIdEspecialista) {
        this.citaEspecialistaIdEspecialista = citaEspecialistaIdEspecialista;
    }

    public Integer getCitaHorarioIdhorario() {
        return citaHorarioIdhorario;
    }

    public void setCitaHorarioIdhorario(Integer citaHorarioIdhorario) {
        this.citaHorarioIdhorario = citaHorarioIdhorario;
    }
}
