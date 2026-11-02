package org.viajesOnline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumericoTest {
  Numerico numerico = new Numerico();

  @Test
  void guardaUnInteger() {
    numerico.validar(1);
    assertEquals(1, numerico.getValor());
  }

  @Test
  void rechazaNull() {
    assertThrows(IllegalArgumentException.class,
            () -> numerico.validar(null));
  }

  @Test
  void tipoIncompatibleLanzaClassCastException() {
    assertThrows(ClassCastException.class,
            () -> numerico.validar("1"));
  }

}