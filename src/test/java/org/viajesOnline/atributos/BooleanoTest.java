package org.viajesOnline.atributos;

import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.CompletarCampos;

import static org.junit.jupiter.api.Assertions.*;

class BooleanoTest {
  private Booleano boleano = new Booleano();
  @Test
  void guardaUnBoolean() {
    boleano.validar(false);
    assertEquals(false, boleano.getValor());
  }

  @Test
  void rechazaNull() {
    assertThrows(CompletarCampos.class,
            () -> boleano.validar(null));
  }

  @Test
  void rechazatipoIncompatible() {
    assertThrows(CompletarCampos.class,
            () -> boleano.validar(1));
  }
}