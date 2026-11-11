package org.viajesOnline.criterios;

import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ANDTest {
  Criterio crt1 = mock(Criterio.class);
  Criterio crt2 = mock(Criterio.class);
  Catalogo catalogo = mock(Catalogo.class);
  AND and = new AND(crt1, crt2);

  @Test
  void pasaCuandoAmbosCriteriosSeCumplen() {
    when(crt1.satisfaceCriterio(catalogo)).thenReturn(true);
    when(crt2.satisfaceCriterio(catalogo)).thenReturn(true);

    assertTrue(and.satisfaceCriterio(catalogo));
    verify(crt1).satisfaceCriterio(catalogo);
    verify(crt2).satisfaceCriterio(catalogo);
  }

  @Test
  void rebotaCuandoElPrimeroNoSeCumple() {
    when(crt1.satisfaceCriterio(catalogo)).thenReturn(false);

    assertFalse(and.satisfaceCriterio(catalogo));
    verify(crt1).satisfaceCriterio(catalogo);
    verifyNoInteractions(crt2); // veo si funciona el shortcircuit.
  }

  @Test
  void rebotaCuandoSoloElSegundoNoSeCumple() {

    when(crt1.satisfaceCriterio(catalogo)).thenReturn(true);
    when(crt2.satisfaceCriterio(catalogo)).thenReturn(false);

    assertFalse(and.satisfaceCriterio(catalogo));
    verify(crt1).satisfaceCriterio(catalogo);
    verify(crt2).satisfaceCriterio(catalogo);
  }
}