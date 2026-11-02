package org.viajesOnline;

import java.util.Date;

public class Fecha implements  TipoDato{
  private Date valor;

  @Override
  public void validar(Object valor) {
    this.valor = (Date) valor;
  }

}
