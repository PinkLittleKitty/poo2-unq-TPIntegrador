package org.viajesOnline.atributos;

import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.CompletarCampos;

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
    assertThrows(CompletarCampos.class,
            () -> numerico.validar(null));
  }

  @Test
  void rechazaTipoIncompatible() {
    assertThrows(CompletarCampos.class,
            () -> numerico.validar("1"));
  }

}