package org.viajesOnline.criterios;

import org.viajesOnline.catalogo.Item;

import java.util.ArrayList;
import java.util.List;

public class Busqueda {
  private List<Item> items = new ArrayList<>();

  public List<Item> buscarEnCatalogo(Criterio criterio){
    return items.stream().filter(criterio::satisfaceCriterio).toList();
  }

  public List<Item> getItems() {
    return List.copyOf(items);
  }

  public void addItem(Item i){
    items.add(i);
  }

  public void delItem(Item i){
    items.remove(i);
  }
}
