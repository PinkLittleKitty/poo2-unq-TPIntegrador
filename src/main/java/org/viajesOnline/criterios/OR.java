package org.viajesOnline.criterios;

import org.viajesOnline.Catalogo;

public class OR implements Criterio{
  private Criterio crt1;
  private Criterio crt2;

  public OR(Criterio crt1, Criterio crt2) {
    this.crt1 = crt1;
    this.crt2 = crt2;
  }

  @Override
  public boolean sastifaceCriterio(Catalogo cat) {
    return crt1.sastifaceCriterio(cat) || crt2.sastifaceCriterio(cat);
  }
}
