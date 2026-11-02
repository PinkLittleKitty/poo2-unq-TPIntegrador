package org.viajesOnline;

import org.junit.jupiter.api.Test;
import org.viajesOnline.atributos.Booleano;

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
    assertThrows(IllegalArgumentException.class,
            () -> boleano.validar(null));
  }

  @Test
  void tipoIncompatibleLanzaClassCastException() {
    assertThrows(ClassCastException.class,
            () -> boleano.validar(1));
  }
}