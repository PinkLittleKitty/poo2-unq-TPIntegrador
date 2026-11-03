package org.viajesOnline.atributos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FechaTest {
  private Fecha fecha = new Fecha();
  private LocalDate unaFecha = LocalDate.of(2026, 11, 2);
  @Test
  void guardaUnaFecha() {
    fecha.validar(unaFecha);
    assertEquals(unaFecha, fecha.getValor());
  }

  @Test
  void rechazaNull() {
    assertThrows(IllegalArgumentException.class,
            () -> fecha.validar(null));
  }

  @Test
  void tipoIncompatibleLanzaClassCastException() {
    assertThrows(ClassCastException.class,
            () -> fecha.validar(123));
  }
}