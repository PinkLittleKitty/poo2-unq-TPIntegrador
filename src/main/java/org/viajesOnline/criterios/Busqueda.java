package org.viajesOnline.criterios;

import org.viajesOnline.Catalogo;

import java.util.ArrayList;
import java.util.List;

public class Busqueda {
  private List<Catalogo> items = new ArrayList<>();

  public List<Catalogo> buscarEnCatalogo(Criterio criterio){
    return items.stream().filter(criterio::sastifaceCriterio).toList();
  }

}
