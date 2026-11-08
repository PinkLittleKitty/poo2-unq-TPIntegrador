package org.viajesOnline.criterios;

import org.viajesOnline.Catalogo;

public class Nombre implements Criterio{
  private String nombre;

  public Nombre(String nombre) {
    this.nombre = nombre.toLowerCase();
  }

  @Override
  public boolean sastifaceCriterio(Catalogo cat) {
    return false;
  }
}
