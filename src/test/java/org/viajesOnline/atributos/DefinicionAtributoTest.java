package org.viajesOnline.atributos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefinicionAtributoTest {
  @Test
  void creaAtributoConDatosValidos() {
    DefinicionAtributo atributo =
            new DefinicionAtributo("RatingEstrellas", true, 5, new Numerico());

    assertEquals("RatingEstrellas", atributo.getNombre());
    assertEquals(5, atributo.getValor());
    assertTrue(atributo.esObligatorio());
  }

  @Test
  void rechazaNombreNulo() {
    assertThrows(IllegalArgumentException.class,
            () -> new DefinicionAtributo(null, true, 5, new Numerico()));
  }

  @Test
  void rechazaValorNulo() {
    assertThrows(IllegalArgumentException.class,
            () -> new DefinicionAtributo("RatingEstrellas", true, null, new Numerico()));
  }

  @Test
  void rechazaTipoNulo() {
    assertThrows(IllegalArgumentException.class,
            () -> new DefinicionAtributo("RatingEstrellas", true, 5, null));
  }

  @Test
  void rechazaValorQueNoCorrespondeAlTipo() {
    IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> new DefinicionAtributo("RatingEstrellas", true, "cinco", new Numerico())
    );

    assertEquals("Valor y tipo no corresponden al atributo", excepcion.getMessage());
  }

  @Test
  void getTipoDevuelveElTipoRecibido() {
    Numerico tipo = new Numerico();

    DefinicionAtributo atributo =
            new DefinicionAtributo("RatingEstrellas", true, 5, tipo);

    assertSame(tipo, atributo.getTipo());
  }
}
