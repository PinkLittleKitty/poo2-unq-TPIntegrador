package org.viajesOnline.atributos;

import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.CompletarCampos;

import static org.junit.jupiter.api.Assertions.*;

class DefinicionAtributoTest {
  @Test
  void creaAtributoConDatosValidos() {
    DefinicionAtributo atributo =
            new DefinicionAtributo("RatingEstrellas", 5, new Numerico());

    assertEquals("RatingEstrellas", atributo.getNombre());
    assertEquals(5, atributo.getValor());
  }

  @Test
  void rechazaNombreNulo() {
    assertThrows(CompletarCampos.class,
            () -> new DefinicionAtributo(null, 5, new Numerico()));
  }

  @Test
  void rechazaValorNulo() {
    assertThrows(CompletarCampos.class,
            () -> new DefinicionAtributo("RatingEstrellas", null, new Numerico()));
  }

  @Test
  void rechazaTipoNulo() {
    assertThrows(CompletarCampos.class,
            () -> new DefinicionAtributo("RatingEstrellas", 5, null));
  }

  @Test
  void rechazaValorQueNoCorrespondeAlTipo() {
   assertThrows(
           CompletarCampos.class,
            () -> new DefinicionAtributo("RatingEstrellas", "cinco", new Numerico())
    );
  }

  @Test
  void getTipoDevuelveElTipoRecibido() {
    Numerico tipo = new Numerico();

    DefinicionAtributo atributo =
            new DefinicionAtributo("RatingEstrellas", 5, tipo);

    assertSame(tipo, atributo.getTipo());
  }
}
