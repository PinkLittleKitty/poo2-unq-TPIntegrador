package org.viajesOnline.atributos;

import org.apache.commons.lang3.Validate;
import org.viajesOnline.exceptions.CompletarCampos;

import java.util.Objects;

public class AtributoDinamico {

  private String nombre;
  private Object valor;
  private TipoDato tipo;

  public AtributoDinamico(String _nombre, Object _valor, TipoDato _tipo) {
    this.verificaciones(_nombre,_valor,_tipo);
    this.nombre = _nombre;
    this.valor = _valor;
    this.tipo = _tipo;
  }

  private static void verificaciones(String _nombre, Object _valor, TipoDato _tipo){
    try{
      Objects.requireNonNull(_nombre);
      Validate.notBlank(_nombre);
      Objects.requireNonNull(_valor);
      Objects.requireNonNull(_tipo);
      _tipo.validar(_valor);
    } catch (Exception e) {
      throw new CompletarCampos("Se requiere que se completen todos los campos");
    }
  }

  public String getNombre() {
    return nombre;
  }

  public Object getValor() {
    return valor;
  }

  public TipoDato getTipo() {
    return tipo;
  }
}
