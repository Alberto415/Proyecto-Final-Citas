package com.uam.citas.DTO;

public class DetallePsicologicaDTO {

    private Integer idDetallePsicologica;
    private String descripcionEmocional;
    private String motivoConsilla;
    private Integer citaIdCita;
    private Integer citaUsuarioIdUsuario;
    private Integer citaUsuarioExpedienteIdExpediente;
    private Integer citaEspecialistaIdEspecialista;
    private Integer citaHorarioIdhorario;

    public Integer getIdDetallePsicologica() {
        return idDetallePsicologica;
    }

    public void setIdDetallePsicologica(Integer idDetallePsicologica) {
        this.idDetallePsicologica = idDetallePsicologica;
    }

    public String getDescripcionEmocional() {
        return descripcionEmocional;
    }

    public void setDescripcionEmocional(String descripcionEmocional) {
        this.descripcionEmocional = descripcionEmocional;
    }

    public String getMotivoConsilla() {
        return motivoConsilla;
    }

    public void setMotivoConsilla(String motivoConsilla) {
        this.motivoConsilla = motivoConsilla;
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
