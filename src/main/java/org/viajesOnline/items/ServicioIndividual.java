package org.viajesOnline.items;

import org.viajesOnline.atributos.DefinicionAtributo;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.apache.commons.lang3.Validate;
import org.viajesOnline.utils.Periodo;

import java.util.ArrayList;
import java.util.List;

public class ServicioIndividual extends Item {
  private double precioBase;
  private int idReserva; //??????
  private List<DefinicionAtributo> atributosDinamicos = new ArrayList<DefinicionAtributo>();

  private static void verificacionCons(int idReserva, double precioBase){
    try{
      Validate.validState(idReserva > 0);
      Validate.validState(precioBase > 0);
    } catch (Exception e) {
      throw new CompletarCampos("Se requiere que se completen todos los campos");
    }
  }

  private void verificacionNuevoAtt(DefinicionAtributo att){
    try{
      Validate.notNull(att, "Se requiere un atributo");
    } catch (Exception e) {
      throw new CompletarCampos("Se requiere que se completen todos los campos");
    }
  }

  public ServicioIndividual(String nombre, String descripcion, Cupo cupo, int idReserva, String proveedor, double precioBase, Categoria categoria, Periodo periodo){
    super(nombre, descripcion, cupo, periodo, categoria, proveedor);
    this.verificacionCons(idReserva, precioBase);
    this.idReserva = idReserva;
    this.precioBase = precioBase;
  }

  public void agregarAtributoExtra(DefinicionAtributo _atributo){
    //_atributo deberia internamente verificar que no tiene nada null
    this.verificacionNuevoAtt(_atributo);
    atributosDinamicos.add(_atributo);
  }

  public List<DefinicionAtributo> getAtributosDinamicos() {
    return List.copyOf(atributosDinamicos);
  }

  public int getIdReserva() {
    return idReserva;
  }

  @Override
  public double getPrecioBase() {
    return precioBase;
  }
}
