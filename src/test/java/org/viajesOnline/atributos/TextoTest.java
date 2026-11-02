package org.viajesOnline;

import org.junit.jupiter.api.Test;
import org.viajesOnline.atributos.Texto;

import static org.junit.jupiter.api.Assertions.*;

class TextoTest {
  private Texto texto = new Texto();
  @Test
  void guardaUnString() {
    texto.validar("una descripcion larga de algo");
    assertEquals("una descripcion larga de algo", texto.getValor());
  }

  @Test
  void rechazaNull() {
    assertThrows(IllegalArgumentException.class,
            () -> texto.validar(null));
  }

  @Test
  void tipoIncompatibleLanzaClassCastException() {
    assertThrows(ClassCastException.class,
            () -> texto.validar(1234));
  }
}