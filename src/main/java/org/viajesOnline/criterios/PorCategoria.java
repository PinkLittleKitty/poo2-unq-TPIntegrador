package org.viajesOnline.criterios;
import org.viajesOnline.Catalogo;
import org.viajesOnline.Categoria;

public class PorCategoria implements Criterio {
  private Categoria categoria;

  public PorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean satisfaceCriterio(Catalogo cat) {
    return this.getCategoria().equals(cat.categoria());
  }

  public Categoria getCategoria() {
    return categoria;
  }
}
