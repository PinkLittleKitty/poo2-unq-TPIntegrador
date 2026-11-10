package org.viajesOnline.criterios;

import org.viajesOnline.Catalogo;
import org.viajesOnline.ServicioIndividual;

public class Categoria implements Criterio {
  private Categoria categoria;

  public Categoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean sastifaceCriterio(ServicioIndividual cat) {
    return this.getCategoria() == cat.categoria();
  }

  public Categoria getCategoria() {
    return categoria;
  }
}
