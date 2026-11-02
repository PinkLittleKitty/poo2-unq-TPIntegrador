package org.viajesOnline;

public class Booleano implements  TipoDato{
    private Boolean valor;

    @Override
    public void validar(Object valor) {
      this.valor = (Boolean) valor;
    }

}
