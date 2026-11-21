package org.viajesOnline.criterios;
import org.viajesOnline.items.Item;
import org.viajesOnline.items.Categoria;

public class PorCategoria implements Criterio {
  private Categoria categoria;

  public PorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean satisfaceCriterio(Item cat) {
    return this.getCategoria().equals(cat.categoria());
  }

  public Categoria getCategoria() {
    return categoria;
  }
}
