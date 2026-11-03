package org.viajesOnline.utils;

public class Usuario {
    private String Nombre;
    private String email;
    private Direccion domicilio;
    private String CUIT;
    private String DNI;
    private Boolean esResponsableInscripto;

    public String getNombre() {
        return Nombre;
    }

    public String getEmail() {
        return email;
    }

    public Direccion getDomicilio() {
        return domicilio;
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