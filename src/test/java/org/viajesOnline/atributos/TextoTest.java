package org.viajesOnline.atributos;

import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.CompletarCampos;

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
    assertThrows(CompletarCampos.class,
            () -> texto.validar(null));
  }

  @Test
  void rechazaTipoIncompatible() {
    assertThrows(CompletarCampos.class,
            () -> texto.validar(1234));
  }
}