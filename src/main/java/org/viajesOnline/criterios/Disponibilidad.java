package org.viajesOnline.criterios;
import org.viajesOnline.items.Item;
import org.viajesOnline.utils.Periodo;

public class Disponibilidad implements Criterio{
  private Periodo rangoFecha;

  public Periodo getRangoFecha() {
    return rangoFecha;
  }

  public Disponibilidad(Periodo rangoFecha) {
    this.rangoFecha = rangoFecha;
  }

  @Override
  public boolean satisfaceCriterio(Item cat) {
    return cat.estaDisponibleEn(rangoFecha);
  }
}
