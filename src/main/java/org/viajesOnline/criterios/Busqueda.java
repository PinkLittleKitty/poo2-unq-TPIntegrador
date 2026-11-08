package org.viajesOnline.criterios;

import org.viajesOnline.Catalogo;

import java.util.ArrayList;
import java.util.List;

public class Busqueda {
  private List<Catalogo> items = new ArrayList<>();

  public List<Catalogo> buscarEnCatalogo(Criterio criterio){
    return items.stream().filter(criterio::sastifaceCriterio).toList();
  }

  public List<Catalogo> getItems() {
    return List.copyOf(items);
  }

  public void addItem(Catalogo i){
    items.add(i);
  }

  public void delItem(Catalogo i){
    items.remove(i);
  }
}
