package org.viajesOnline;

public abstract class Catalogo {
  private String nombre;
  private String descripcion;
  private double precioBase;
  private int cupoDisponible;
  private int descuento = 0;

  public void incrementarCupo() {
    cupoDisponible += 1;
  }

  public int descuento() {
    return descuento;
  }

  public void decrementarCupo() {
    cupoDisponible -= 1;
  }

  public double precioFinal(){
    return this.getPrecioBase() * ( 1 - this.descuento()/100);
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

