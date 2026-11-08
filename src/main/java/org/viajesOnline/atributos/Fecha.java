package org.viajesOnline.atributos;

import org.viajesOnline.exceptions.CompletarCampos;

import java.time.LocalDate;
import java.util.Objects;

public class Fecha implements TipoDato {
  private LocalDate valor;

  @Override
  public void validar(Object _valor){
    try {
      Objects.requireNonNull(_valor);
      this.valor = (LocalDate) _valor;
    } catch (Exception e) {
      throw new CompletarCampos("Valor y tipo no corresponden al atributo");
    }
  }


  public LocalDate getValor() {//tests mas simples
    return valor;
  }
}
