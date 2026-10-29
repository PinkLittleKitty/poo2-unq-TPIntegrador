package org.viajesOnline;

public abstract class Catalogo {
  private String nombre;
  private String descripcion;
  private double precioBase;
  private int cupoDisponible;

  public void recuperarCupo(int cupo) {
    cupoDisponible += cupo;
  }

  public void liberarCupo(int cupo) {
    cupoDisponible -= cupo;
  }

  public double precioFinal(int descuento){
    return this.getPrecioBase() * (descuento/100);
  }

  public Catalogo(String nombre, String descripcion, double precioBase, int cupoDisponible){
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precioBase = precioBase;
    this.cupoDisponible = cupoDisponible;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public double getPrecioBase() {
    return precioBase;
  }

  public int getCupoDisponible() {
    return cupoDisponible;
  }
}

