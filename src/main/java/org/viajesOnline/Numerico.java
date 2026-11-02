package org.viajesOnline;

public class Numerico implements TipoDato {
  private Integer valor;
  @Override
  public void validar(Object valor) {
    this.valor = (Integer) valor;
  }
}
