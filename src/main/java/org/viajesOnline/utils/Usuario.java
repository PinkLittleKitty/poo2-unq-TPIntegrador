package org.viajesOnline.utils;

public class Usuario {
    private String nombre;
    private String email;
    private String CUIT;
    private String DNI;
    private Boolean esResponsableInscripto;

    public Usuario(String nombre, String email, String cuit, String dni, Boolean esResponsableInscripto) {
        this.nombre = nombre;
        this.email = email;
        this.CUIT = cuit;
        this.DNI = dni;
        this.esResponsableInscripto = esResponsableInscripto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getCUIT() {
        return CUIT;
    }

    public String getDNI() {
        return DNI;
    }

    public Boolean getEsResponsableInscripto() {
        return esResponsableInscripto;
    }
}