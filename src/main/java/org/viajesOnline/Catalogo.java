package org.viajesOnline;

import org.viajesOnline.reservas.Cupo;

public abstract class Catalogo {
  private String nombre;
  private String descripcion;
  private double precioBase;
  private int cupoDisponible;
  private int descuento = 0;
  private Cupo cupo;

  public int descuento() {
    return descuento;
  }

  private boolean esDescuentoValido(int _desc){
    return _desc >= 0 && _desc <= 100;
  }

  public void darDescuento(int descuento){
    if (esDescuentoValido(descuento)) {
      this.descuento = descuento;
    } else {
      throw new IllegalArgumentException("El descuento es de numeros de 1 al 100");
    }
  }

  private double descuentoAplicable(){
    return this.getPrecioBase() * this.getDescuento()/100;
  }

  public double precioFinal() {
    return this.getPrecioBase() - this.descuentoAplicable();
  }

  public Catalogo(String nombre, String descripcion, double precioBase, Cupo cupoDisponible){
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precioBase = precioBase;
    this.cupo = cupoDisponible;
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
    return cupo.getCupoDisponible();
  }
  public void incrementarCupo() { cupo.incrementarCupo(); }
  public void decrementarCupo() { cupo.decrementarCupo(); }

  public int getDescuento() {
    return descuento;
  }
}

