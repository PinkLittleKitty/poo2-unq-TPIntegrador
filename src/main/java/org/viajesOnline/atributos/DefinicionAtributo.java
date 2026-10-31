package org.viajesOnline.atributos;

public class DefinicionAtributo {
  private String nombre;
  private boolean esObligatorio;
  private Object valor;

  private DefinicionAtributo() {
  }

  public static DefinicionAtributo crearNuevoAtributo(String nombre, boolean esObligatorio, Object valor) {
    validarAtributoDinamico();
    return new DefinicionAtributo();
  }

  private static void validarAtributoDinamico() {

  }

  public String getNombre() {
    return nombre;
  }

  public Object getValor() {
    return valor;
  }

  public boolean isEsObligatorio() {
    return esObligatorio;
  }
}
