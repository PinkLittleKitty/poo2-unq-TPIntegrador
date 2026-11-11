package org.viajesOnline.criteriosTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.criterios.PrecioMax;
import org.viajesOnline.exceptions.CompletarCampos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PrecioMaxTest {
  private PrecioMax precioMax = new PrecioMax(23.05);
  private Catalogo catalogo = mock(Catalogo.class);

  @Test
  void getValorEsCorrecto() {
    assertEquals(23.05, precioMax.getValor());
  }

  @Test
  void rechazaSiPrecioEsNegativoOCero() {
    assertThrows(CompletarCampos.class,
            () -> new PrecioMax(0.0));
    assertThrows(CompletarCampos.class,
            () -> new PrecioMax(-0.1));
  }

  @Test
  void satisfaceCriterioCuandoElPrecioEsMenorOigual() {
    when(catalogo.getPrecioBase()).thenReturn(23.05);

    Catalogo cat2 = mock(Catalogo.class);
    when(cat2.getPrecioBase()).thenReturn(22.25);

    assertTrue(precioMax.satisfaceCriterio(catalogo));
    assertTrue(precioMax.satisfaceCriterio(cat2));
  }

  @Test
  void NosatisfaceCriterioCuandoElPrecioEsMayor() {
    when(catalogo.getPrecioBase()).thenReturn(50.0);

    assertFalse(precioMax.satisfaceCriterio(catalogo));
  }
}