package org.viajesOnline.atributosTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.atributos.Fecha;
import org.viajesOnline.exceptions.CompletarCampos;

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
    assertThrows(CompletarCampos.class,
            () -> fecha.validar(null));
  }

  @Test
  void rechazaTipoIncompatible() {
    assertThrows(CompletarCampos.class,
            () -> fecha.validar(123));
  }
}