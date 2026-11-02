package org.viajesOnline;

public class Texto implements TipoDato{
  private String valor;
  private static void validacionNotNull(Object arg){ // Object... indica que agarra cualquier numero de parametros
    // TO DO: encontrar una forma que tarde menos en checkear cada cosa. Si hay algo que venga con java o se tarde menos sacar.
    if (arg==null) {
      throw new IllegalArgumentException("Se requiere que todos los atributos sean completados");
    }
  }

  @Override
  public void validar(Object _valor) {
    validacionNotNull(_valor);
    this.valor = (String) _valor;
  }

  public String getValor() {//tests mas simples
    return valor;
  }
}
