package org.viajesOnline.criterios;

import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.utils.Periodo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DisponibilidadTest {
  private Periodo periodo = mock(Periodo.class);
  private Disponibilidad criterio = new Disponibilidad(periodo);
  private Catalogo catalogo = mock(Catalogo.class);

  @Test
  void getFechaEsCorrecto() {
    Disponibilidad criterio1 = new Disponibilidad(new Periodo(LocalDate.of(2026,11,04), LocalDate.of(2026,11,10)));
    assertEquals(new Periodo(LocalDate.of(2026,11,04), LocalDate.of(2026,11,10)),criterio1.getRangoFecha());
  }

  @Test
  void satisfaceCriterio() {
    when(catalogo.estaDisponibleEn(periodo)).thenReturn(true);
    assertTrue(criterio.satisfaceCriterio(catalogo));
  }


  @Test
  void noSatisfaceCriterio() {
    when(catalogo.estaDisponibleEn(periodo)).thenReturn(false);
    assertFalse(criterio.satisfaceCriterio(catalogo));
  }
}