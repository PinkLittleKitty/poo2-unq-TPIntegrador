package org.viajesOnline;

public class Texto implements TipoDato{
  private String valor;

  @Override
  public void validar(Object valor) {
    this.valor = (String) valor;
  }

}
