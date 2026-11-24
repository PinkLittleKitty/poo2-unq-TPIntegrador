package org.viajesOnline.items;

import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.CalendarioCupos;
import org.viajesOnline.utils.Periodo;

import java.util.ArrayList;
import java.util.List;

public class Paquete extends Item {
  private List<Item> items = new ArrayList<>();

  public Paquete(String nombre, String descripcion, CalendarioCupos calendario, Categoria categoria, String proveedor) {
    super(nombre, descripcion, calendario, categoria, proveedor);
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

  @Override
  public boolean estaDisponibleEn(Periodo periodo) {
    boolean paqueteDisponible = super.estaDisponibleEn(periodo);
    boolean itemsDisponibles = !items.isEmpty() && items.stream().allMatch(i -> i.estaDisponibleEn(periodo));

    return paqueteDisponible && itemsDisponibles;
  }

  @Override
  public void decrementarCupo(Periodo periodo) {
    super.decrementarCupo(periodo);

    for (Item item : this.items) {
      item.decrementarCupo(periodo);
    }
  }

  @Override
  public void incrementarCupo(Periodo periodo) {
    super.incrementarCupo(periodo);

    for (Item item : this.items) {
      item.incrementarCupo(periodo);
    }
  }
}
