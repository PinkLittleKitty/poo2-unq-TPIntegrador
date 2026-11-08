package org.viajesOnline.criterios;

import org.apache.commons.lang3.Validate;
import org.viajesOnline.Catalogo;
import org.viajesOnline.exceptions.CompletarCampos;

public class PrecioMax implements Criterio{
  private double valor;

  public double getValor() {
    return valor;
  }

  public PrecioMax(double valor) {
    verificar(valor);
    this.valor = valor;
  }

  private static void verificar(double valor) {
    try{
      Validate.validState(valor > 0);
    } catch (Exception e) {
      throw new CompletarCampos("falla criterio precio");
    }
  }

  @Override
  public boolean sastifaceCriterio(Catalogo cat) {
    return this.getValor() >= cat.getPrecioBase();
  }
}
