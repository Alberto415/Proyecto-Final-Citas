package com.uam.citas.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Especialista")
public class EspecialistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEspecialista")
    private Long idEspecialista;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, length = 100)
    private String correo;

    @Column(length = 20)
    private String telefono;

    @Column(name = "area")
    private String area;

    public EspecialistaEntity() {
    }

    public Long getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(Long idEspecialista) {
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