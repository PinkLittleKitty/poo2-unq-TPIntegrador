package org.viajesOnline;

import org.viajesOnline.atributos.DefinicionAtributo;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.apache.commons.lang3.Validate;
import org.viajesOnline.utils.Periodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ServicioIndividual extends Catalogo {
  private int idReserva; //??????
  private String proveedor;
  private List<DefinicionAtributo> atributosDinamicos = new ArrayList<DefinicionAtributo>();

  private static void verificacionCons(String proveedor,int idReserva){
    try{
      Objects.requireNonNull(proveedor);
      Validate.notBlank(proveedor);
      Validate.validState(idReserva > 0);
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

  public ServicioIndividual(String nombre, String descripcion, Cupo cupo, int idReserva, String proveedor, double precio, Categoria categoria, Periodo periodo){
    super(nombre, descripcion, precio, cupo, periodo, categoria);
    this.verificacionCons(proveedor,idReserva);
    this.idReserva = idReserva;
    this.proveedor = proveedor;
  }

  public void agregarAtributoExtra(DefinicionAtributo _atributo){
    //_atributo deberia internamente verificar que no tiene nada null
    this.verificacionNuevoAtt(_atributo);
    atributosDinamicos.add(_atributo);
  }

  public List<DefinicionAtributo> getAtributosDinamicos() {
    return List.copyOf(atributosDinamicos);
  }

  public String proveedor(){
    return proveedor;
  }

  public int getIdReserva() {
    return idReserva;
  }
}
