package org.viajesOnline.items;

import org.apache.commons.lang3.Validate;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import java.util.Objects;

public abstract class Item {
  private String nombre;
  private String descripcion;
  private int descuento = 0;
  private Cupo cupo;
  private Periodo periodoVigencia;
  private Categoria categoria;
  private String proveedor;

  public Item(String nombre, String descripcion, Cupo cupoDisponible, Periodo periodoVigencia, Categoria categoria, String proveedor){
    validar(nombre, descripcion,cupoDisponible,periodoVigencia, categoria, proveedor);
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.cupo = cupoDisponible;
    this.periodoVigencia = periodoVigencia;
    this.categoria = categoria;
    this.proveedor = proveedor;
  }

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

  private static void validar(String nombre, String descripcion, double precioBase, Cupo cupoDisponible, Periodo periodo,Categoria categoria, String proveedor){
    try {
      Objects.requireNonNull(periodo);
      Objects.requireNonNull(categoria);
      Objects.requireNonNull(nombre);
      Objects.requireNonNull(descripcion);
      Objects.requireNonNull(cupoDisponible);
      Objects.requireNonNull(proveedor);
      Validate.notBlank(proveedor);
      Validate.notBlank(nombre, descripcion, "Es obligatorio completar estos campos");
      Validate.validState(precioBase > 0);
    } catch (Exception e) {
      throw new CompletarCampos("Se requiere que se completen todos los campos");
    }
  }

//  public boolean estaDisponibleEn(LocalDate fecha) {
//    return this.getCupoDisponible() > 0 && this.periodoVigencia.incluye(fecha);
//  }

  public boolean estaDisponibleEn(Periodo periodo) {
    return this.getCupoDisponible() > 0 && this.periodoVigencia.seSuperponeCon(periodo);
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public abstract double getPrecioBase();

  public int getCupoDisponible() {
    return cupo.getCupoDisponible();
  }

  public Cupo getCupo() { return cupo; }

  public Periodo getPeriodoVigencia() { return periodoVigencia; }

  public void incrementarCupo() { cupo.incrementarCupo(); }
  public void decrementarCupo() { cupo.decrementarCupo(); }

  public int getDescuento() {
    return descuento;
  }

  public String proveedor(){
    return proveedor;
  }

  public Categoria categoria() {
    return categoria;
  }
}

