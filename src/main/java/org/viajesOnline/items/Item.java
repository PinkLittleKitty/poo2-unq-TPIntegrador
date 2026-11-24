package org.viajesOnline.items;

import org.apache.commons.lang3.Validate;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.CalendarioCupos;
import org.viajesOnline.utils.Periodo;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public abstract class Item {
  private String nombre;
  private String descripcion;
  private int descuento = 0;
  private CalendarioCupos calendarioCupos;
  private Categoria categoria;
  private String proveedor;

  public Item(String nombre, String descripcion, CalendarioCupos calendario, Categoria categoria, String proveedor){

    validar(nombre, descripcion, calendario, categoria, proveedor);
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.calendarioCupos = calendario;
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

  private static void validar(String nombre, String descripcion, CalendarioCupos calendario, Categoria categoria, String proveedor){
    try {
      Objects.requireNonNull(calendario);
      Objects.requireNonNull(categoria);
      Objects.requireNonNull(nombre);
      Objects.requireNonNull(descripcion);
      Objects.requireNonNull(proveedor);
      Validate.notBlank(proveedor);
      Validate.notBlank(nombre, descripcion, "Es obligatorio completar estos campos");
    } catch (Exception e) {
      throw new CompletarCampos("Se requiere que se completen todos los campos");
    }
  }

  public boolean estaDisponibleEn(Periodo periodo) {
    return this.calendarioCupos.estaDisponibleEn(periodo);
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public abstract double getPrecioBase();

  public Periodo getPeriodoVigencia() { return this.calendarioCupos.getPeriodoVigencia(); }

  public Map<LocalDate, Cupo> getCuposPorDia(Periodo periodo) {
    return this.calendarioCupos.getCuposPorDia(periodo);
  }

  public Map<LocalDate, Cupo> getCuposPorDia() {
    return this.calendarioCupos.getCuposPorDia();
  }

  public Cupo getCupoDelDia(LocalDate dia) {
    return this.calendarioCupos.getCupoDelDia(dia);
  }

  public void incrementarCupo(Periodo periodo) { calendarioCupos.incrementarCupo(periodo); }
  public void decrementarCupo(Periodo periodo) { calendarioCupos.decrementarCupo(periodo); }

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

