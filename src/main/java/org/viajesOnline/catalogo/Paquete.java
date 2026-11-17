package org.viajesOnline.catalogo;

import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import java.util.ArrayList;
import java.util.List;

public class Paquete extends Catalogo{
  private List<Catalogo> items = new ArrayList<>();

  public Paquete(String nombre, String descripcion, double precioBase, Cupo cupoDisponible, Periodo periodo, Categoria categoria, String proveedor) {
    super(nombre, descripcion, precioBase, cupoDisponible, periodo, categoria, proveedor);
  }

  public List<Catalogo> verItems() {
    return List.copyOf(items);
  }

  public void agregarItem(Catalogo item) {
    this.items.add(item);
  }

  @Override
  public double getPrecioBase() {
    return items.stream()
            .mapToDouble(Catalogo::getPrecioBase)
            .sum();
  }
}
