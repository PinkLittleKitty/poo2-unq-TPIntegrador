package org.viajesOnline.criterios;

import org.viajesOnline.Catalogo;

public class NOT implements Criterio{
  private Criterio crt1;

  public NOT(Criterio crt1) {
    this.crt1 = crt1;
  }

  @Override
  public boolean satisfaceCriterio(Catalogo cat) {
    return !crt1.satisfaceCriterio(cat);
  }
}
