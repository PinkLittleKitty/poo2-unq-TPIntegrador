package org.viajesOnline.criterios;

import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.exceptions.CompletarCampos;

import java.util.List;

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
  void sastifaceCriterioCuandoElPrecioEsMenorOigual() {
    when(catalogo.getPrecioBase()).thenReturn(23.05);

    Catalogo cat2 = mock(Catalogo.class);
    when(cat2.getPrecioBase()).thenReturn(22.25);

    assertTrue(precioMax.sastifaceCriterio(catalogo));
    assertTrue(precioMax.sastifaceCriterio(cat2));
  }

  @Test
  void NosastifaceCriterioCuandoElPrecioEsMayor() {
    when(catalogo.getPrecioBase()).thenReturn(50.0);

    assertFalse(precioMax.sastifaceCriterio(catalogo));
  }

  @Test
  void busquedaFiltraLosCatalogosPorPrecioMax() {
    Busqueda busqueda = new Busqueda();
    Catalogo coincide = mock(Catalogo.class);
    Catalogo noCoincide = mock(Catalogo.class);

    when(coincide.getPrecioBase()).thenReturn(21.0);
    when(noCoincide.getPrecioBase()).thenReturn(100.0);

    busqueda.addItem(coincide);
    busqueda.addItem(noCoincide);

    List<Catalogo> resultado =
            busqueda.buscarEnCatalogo(precioMax);

    assertEquals(List.of(coincide), resultado);
  }
}