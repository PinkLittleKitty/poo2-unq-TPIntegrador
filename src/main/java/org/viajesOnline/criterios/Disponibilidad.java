package org.viajesOnline.criterios;
import org.viajesOnline.Catalogo;
import java.time.LocalDate;

public class Disponibilidad implements Criterio{
  private LocalDate fecha;

  public Disponibilidad(LocalDate fecha) {
    this.fecha = fecha;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  @Override
  public boolean sastifaceCriterio(Catalogo cat) {
    return false; // cat.hayCupoDisponibleEn(fecha)
  }
}
