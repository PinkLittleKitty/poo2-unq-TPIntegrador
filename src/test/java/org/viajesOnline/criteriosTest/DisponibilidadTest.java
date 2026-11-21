package org.viajesOnline.criteriosTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Item;
import org.viajesOnline.criterios.Disponibilidad;
import org.viajesOnline.utils.Periodo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DisponibilidadTest {
  private Periodo periodo = mock(Periodo.class);
  private Disponibilidad criterio = new Disponibilidad(periodo);
  private Item item = mock(Item.class);

  @Test
  void getFechaEsCorrecto() {
    Disponibilidad criterio1 = new Disponibilidad(new Periodo(LocalDate.of(2026,11,04), LocalDate.of(2026,11,10)));
    assertEquals(new Periodo(LocalDate.of(2026,11,04), LocalDate.of(2026,11,10)),criterio1.getRangoFecha());
  }

  @Test
  void satisfaceCriterio() { //el testeo de al menos una fecha para el periodo disponible debe estar en PeriodoTest
    when(item.estaDisponibleEn(periodo)).thenReturn(true);
    assertTrue(criterio.satisfaceCriterio(item));
  }

  @Test
  void noSatisfaceCriterio() {
    when(item.estaDisponibleEn(periodo)).thenReturn(false);
    assertFalse(criterio.satisfaceCriterio(item));
  }
}