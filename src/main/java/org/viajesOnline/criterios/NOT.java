package org.viajesOnline.criterios;

import org.viajesOnline.items.Item;

public class NOT implements Criterio{
  private Criterio crt1;

  public NOT(Criterio crt1) {
    this.crt1 = crt1;
  }

  @Override
  public boolean satisfaceCriterio(Item cat) {
    return !crt1.satisfaceCriterio(cat);
  }
}
