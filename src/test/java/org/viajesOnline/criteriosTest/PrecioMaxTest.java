package org.viajesOnline.criteriosTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Item;
import org.viajesOnline.criterios.PrecioMax;
import org.viajesOnline.exceptions.CompletarCampos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PrecioMaxTest {
  private PrecioMax precioMax = new PrecioMax(23.05);
  private Item item = mock(Item.class);

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
    when(item.getPrecioBase()).thenReturn(23.05);

    Item cat2 = mock(Item.class);
    when(cat2.getPrecioBase()).thenReturn(22.25);

    assertTrue(precioMax.satisfaceCriterio(item));
    assertTrue(precioMax.satisfaceCriterio(cat2));
  }

  @Test
  void NosatisfaceCriterioCuandoElPrecioEsMayor() {
    when(item.getPrecioBase()).thenReturn(50.0);

    assertFalse(precioMax.satisfaceCriterio(item));
  }
}