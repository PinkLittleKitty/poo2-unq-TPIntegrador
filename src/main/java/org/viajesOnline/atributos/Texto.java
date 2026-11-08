package org.viajesOnline.atributos;

import org.apache.commons.lang3.Validate;
import org.viajesOnline.exceptions.CompletarCampos;

import java.util.Objects;

public class Texto implements TipoDato {
  private String valor;

  @Override
  public void validar(Object _valor){
    try {
      Objects.requireNonNull(_valor);
      this.valor = (String) _valor;
      Validate.notBlank(valor);
    } catch (Exception e) {
      throw new CompletarCampos("Valor y tipo no corresponden al atributo");
    }

  }

  public String getValor() {//tests mas simples
    return valor;
  }
}
