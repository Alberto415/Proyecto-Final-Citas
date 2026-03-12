package com.uam.citas.DTO;

public class EspecialistaDTO {
    private int idEspecialista;
    private String nombre;
    private String correo;
    private String telefono;
    private String area;

    public int getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(int idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
