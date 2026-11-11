package org.viajesOnline.criterios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.viajesOnline.Categoria.ALOJAMIENTO;
import static org.viajesOnline.Categoria.VUELO;

class PorCategoriaTest {
  PorCategoria criterio = new PorCategoria(ALOJAMIENTO);
  Catalogo catalogo = mock(Catalogo.class);

  @BeforeEach
  void setUp() {
  }

  @Test
  void satisfaceCriterioCuandoCoincide() {
    when(catalogo.categoria()).thenReturn(ALOJAMIENTO);
    assertTrue(criterio.satisfaceCriterio(catalogo));
  }

  @Test
  void noSatisfaceCriterioCuandoNoCoincide() {
    when(catalogo.categoria()).thenReturn(VUELO);
    assertFalse(criterio.satisfaceCriterio(catalogo));
  }

  @Test
  void getCategoriaEsCorrecto() {
    assertEquals(ALOJAMIENTO, criterio.getCategoria());
  }
}