package org.viajesOnline.atributos;

import org.viajesOnline.exceptions.CompletarCampos;

import java.util.Objects;

public class Booleano implements TipoDato {
  private Boolean valor;

  @Override
  public void validar(Object _valor){
    try {
      Objects.requireNonNull(_valor);
      this.valor = (Boolean) _valor;
    } catch (Exception e) {
      throw new CompletarCampos("Valor y tipo no corresponden al atributo");
    }
  }


  public Boolean getValor() { //tests mas simples
    return valor;
  }
}
