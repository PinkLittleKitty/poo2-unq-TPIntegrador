package org.viajesOnline;

import org.apache.commons.lang3.Validate;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;

import java.util.Objects;

public abstract class Catalogo {
  private String nombre;
  private String descripcion;
  private double precioBase;
  private int descuento = 0;
  private Cupo cupo;

  public void darDescuento(int descuento){
    Validate.inclusiveBetween(0, 100, descuento, "No es un descuento valido");
    this.descuento = descuento;
  }

  private double descuentoAplicable(){
    return this.getPrecioBase() * this.getDescuento()/100;
  }

  public double precioFinal() {
    return this.getPrecioBase() - this.descuentoAplicable();
  }

  private static void validar(String nombre, String descripcion, double precioBase, Cupo cupoDisponible){
    try {
      Objects.requireNonNull(nombre);
      Objects.requireNonNull(descripcion);
      Objects.requireNonNull(cupoDisponible);
      Validate.notBlank(nombre, descripcion, "Es obligatorio completar estos campos");
      Validate.validState(precioBase > 0);
    } catch (Exception e) {
      throw new CompletarCampos("Se requiere que se completen todos los campos");
    }
  }

  public Catalogo(String nombre, String descripcion, double precioBase, Cupo cupoDisponible){
    this.validar(nombre, descripcion,precioBase,cupoDisponible);
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

