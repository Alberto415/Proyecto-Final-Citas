package com.uam.citas.DTO;

public class DetalleMedicaDTO {
    private Integer idDetalleMedica;
    private String motivoConsulta;
    private String alergias;
    private Integer citaIdCita;
    private Integer citaUsuarioIdUsuario;
    private Integer citaUsuarioExpedienteIdExpediente;
    private Integer citaEspecialistaIdEspecialista;
    private Integer citaHorarioIdhorario;

    public Integer getIdDetalleMedica() {
        return idDetalleMedica;
    }

    public void setIdDetalleMedica(Integer idDetalleMedica) {
        this.idDetalleMedica = idDetalleMedica;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
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
