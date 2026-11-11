package org.viajesOnline;

import org.apache.commons.lang3.Validate;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Catalogo {
  private String nombre;
  private String descripcion;
  private double precioBase;
  private int descuento = 0;
  private Cupo cupo;
  private Periodo periodoVigencia;
  private Categoria categoria;

  public Catalogo(String nombre, String descripcion, double precioBase, Cupo cupoDisponible, Periodo periodoVigencia,Categoria categoria){
    validar(nombre, descripcion,precioBase,cupoDisponible,periodoVigencia, categoria);
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precioBase = precioBase;
    this.cupo = cupoDisponible;
    this.periodoVigencia = periodoVigencia;
    this.categoria = categoria;
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

  private static void validar(String nombre, String descripcion, double precioBase, Cupo cupoDisponible, Periodo periodo,Categoria categoria){
    try {
      Objects.requireNonNull(periodo);
      Objects.requireNonNull(categoria);
      Objects.requireNonNull(nombre);
      Objects.requireNonNull(descripcion);
      Objects.requireNonNull(cupoDisponible);
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

  public double getPrecioBase() {
    return precioBase;
  }

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

  public Categoria categoria() {
    return categoria;
  }
}

