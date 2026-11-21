package org.viajesOnline.items;

import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import java.util.ArrayList;
import java.util.List;

public class Paquete extends Item {
  private List<Item> items = new ArrayList<>();

  public Paquete(String nombre, String descripcion, double precioBase, Cupo cupoDisponible, Periodo periodo, Categoria categoria, String proveedor) {
    super(nombre, descripcion, precioBase, cupoDisponible, periodo, categoria, proveedor);
  }

  public List<Item> verItems() {
    return List.copyOf(items);
  }

  public void agregarItem(Item item) {
    this.items.add(item);
  }

  @Override
  public double getPrecioBase() {
    return items.stream()
            .mapToDouble(Item::getPrecioBase)
            .sum();
  }
}
