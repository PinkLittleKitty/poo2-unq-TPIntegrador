package org.viajesOnline.atributosTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.atributos.AtributoDinamico;
import org.viajesOnline.atributos.Numerico;
import org.viajesOnline.exceptions.CompletarCampos;

import static org.junit.jupiter.api.Assertions.*;

class AtributoDinamicoTest {
  @Test
  void creaAtributoConDatosValidos() {
    AtributoDinamico atributo =
            new AtributoDinamico("RatingEstrellas", 5, new Numerico());

    assertEquals("RatingEstrellas", atributo.getNombre());
    assertEquals(5, atributo.getValor());
  }

  @Test
  void constructorRechazaNombreNuloOVacio() {
    assertThrows(CompletarCampos.class, () -> new AtributoDinamico(null, 5, new Numerico()));
    assertThrows(CompletarCampos.class, () -> new AtributoDinamico("", 5, new Numerico()));
    assertThrows(CompletarCampos.class, () -> new AtributoDinamico("   ", 5, new Numerico()));
  }

  @Test
  void rechazaValorNulo() {
    assertThrows(CompletarCampos.class,
            () -> new AtributoDinamico("RatingEstrellas", null, new Numerico()));
  }

  @Test
  void rechazaTipoNulo() {
    assertThrows(CompletarCampos.class,
            () -> new AtributoDinamico("RatingEstrellas", 5, null));
  }

  @Test
  void rechazaValorQueNoCorrespondeAlTipo() {
   assertThrows(
           CompletarCampos.class,
            () -> new AtributoDinamico("RatingEstrellas", "cinco", new Numerico())
    );
  }

  @Test
  void getTipoDevuelveElTipoRecibido() {
    Numerico tipo = new Numerico();

    AtributoDinamico atributo =
            new AtributoDinamico("RatingEstrellas", 5, tipo);

    assertSame(tipo, atributo.getTipo());
  }
}
