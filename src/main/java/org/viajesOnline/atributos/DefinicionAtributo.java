package org.viajesOnline.atributos;

public class DefinicionAtributo {
  private String nombre;
  private boolean esObligatorio;
  private Object valor;
  private TipoDato tipo;

  private static void validacionNotNull(Object... args){ // Object... indica que agarra cualquier numero de parametros
    // TO DO: encontrar una forma que tarde menos en checkear cada cosa. Si hay algo que venga con java o se tarde menos sacar.
    for (Object a : args){
      if (a==null) {
        throw new IllegalArgumentException("Se requiere que todos los atributos sean completados");
      }
    }
  }

  public DefinicionAtributo(String _nombre, boolean _esObligatorio, Object _valor, TipoDato _tipo) {
    validacionNotNull(_nombre,_valor,_tipo);
    validarAtributoDinamico(_valor,_tipo);
    this.nombre = _nombre;
    this.esObligatorio = _esObligatorio;
    this.valor = _valor;
    this.tipo = _tipo;
  }

  private static void validarAtributoDinamico(Object _valor, TipoDato _tipo) {
    try {
      _tipo.validar(_valor);
    }
    catch (ClassCastException e){
      throw new IllegalArgumentException("Valor y tipo no corresponden al atributo");
    }
  }

  public String getNombre() {
    return nombre;
  }

  public Object getValor() {
    return valor;
  }

  public boolean esObligatorio() {
    return esObligatorio;
  }

  public TipoDato getTipo() {
    return tipo;
  }
}
