package org.viajesOnline.criteriosTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.items.Item;
import org.viajesOnline.criterios.PorCategoria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.viajesOnline.items.Categoria.ALOJAMIENTO;
import static org.viajesOnline.items.Categoria.VUELO;

class PorCategoriaTest {
  PorCategoria criterio = new PorCategoria(ALOJAMIENTO);
  Item item = mock(Item.class);

  @BeforeEach
  void setUp() {
  }

  @Test
  void satisfaceCriterioCuandoCoincide() {
    when(item.categoria()).thenReturn(ALOJAMIENTO);
    assertTrue(criterio.satisfaceCriterio(item));
  }

  @Test
  void noSatisfaceCriterioCuandoNoCoincide() {
    when(item.categoria()).thenReturn(VUELO);
    assertFalse(criterio.satisfaceCriterio(item));
  }

  @Test
  void getCategoriaEsCorrecto() {
    assertEquals(ALOJAMIENTO, criterio.getCategoria());
  }
}