package org.viajesOnline;

import org.viajesOnline.atributos.DefinicionAtributo;

import java.util.ArrayList;
import java.util.List;


public class ServicioIndividual extends Catalogo {
  private int idReserva; //??????
  private String proovedor;
  private Categoria categoria;
  private List<DefinicionAtributo> atributosDinamicos = new ArrayList<DefinicionAtributo>();

// Java se asegura que los tipos de sus atributos sean los correctos y nosotros nos aseguramos que no sean null cuando nazca.
//  public boolean esValido(){
//      return true;
//  }

  private static void validacionNotNull(Object... args){ // Object... indica que agarra cualquier numero de parametros
    // TO DO: encontrar una forma que tarde menos en checkear cada cosa. Si hay algo que venga con java o se tarde menos sacar.
    for (Object a : args){
      if (a==null) {
        throw new IllegalArgumentException("Se requiere que todos los atributos obligatorios sean completados");
      }
    }
  }

  public ServicioIndividual(String nombre, String descripcion, int cupo, int idReserva, String proveedor, double precio, Categoria categoria){
    validacionNotNull(nombre,descripcion,cupo,idReserva,proveedor,precio,categoria);
    super(nombre,descripcion,precio,cupo);
    this.idReserva = idReserva; //no se si esto deberia ser obligatorio
    this.proovedor = proveedor;
    this.categoria = categoria;
  }

  public void agregarAtributoExtra(DefinicionAtributo _atributo){
    //_atributo deberia internamente verificar que no tiene nada null
    atributosDinamicos.add(_atributo);
  }

  public List<DefinicionAtributo> getAtributosDinamicos() {
    // TO DO: implementar atributos dinamicos.
    return atributosDinamicos;
  }

  public String proovedor(){
    return proovedor;
  }

  public Categoria categoria(){
    return categoria;
  }
}
