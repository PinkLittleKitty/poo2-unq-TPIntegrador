package org.viajesOnline.items;

import org.viajesOnline.atributos.AtributoDinamico;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.apache.commons.lang3.Validate;
import org.viajesOnline.utils.Periodo;

import java.util.ArrayList;
import java.util.List;

public class ServicioIndividual extends Item {

  private double precioBase;
  private int idReserva; //??????

  private List<AtributoDinamico> atributosDinamicos = new ArrayList<AtributoDinamico>();


  public ServicioIndividual(String nombre, String descripcion, int cupo, int idReserva, String proveedor, double precioBase, Categoria categoria, Periodo periodo){
    super(nombre, descripcion, cupo, periodo, categoria, proveedor);
    verificacionCons(idReserva, precioBase);
    this.idReserva = idReserva;
    this.precioBase = precioBase;
  }


  private static void verificacionCons(int idReserva, double precioBase){
    try{
      Validate.validState(idReserva > 0);
      Validate.validState(precioBase > 0);
    } catch (Exception e) {
      throw new CompletarCampos("Se requiere que se completen todos los campos");
    }
  }


  public void agregarAtributoExtra(AtributoDinamico _atributo){

    this.verificacionNuevoAtt(_atributo);
    atributosDinamicos.add(_atributo);
  }

  private void verificacionNuevoAtt(AtributoDinamico att){
    try{
      Validate.notNull(att, "Se requiere un atributo");
    } catch (Exception e) {
      throw new CompletarCampos("Se requiere que se completen todos los campos");
    }
  }


  public List<AtributoDinamico> getAtributosDinamicos() {
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
