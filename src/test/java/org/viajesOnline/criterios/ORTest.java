package org.viajesOnline.criterios;

import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ORTest {
  Criterio crt1 = mock(Criterio.class);
  Criterio crt2 = mock(Criterio.class);
  Catalogo catalogo = mock(Catalogo.class);
  OR or = new OR(crt1, crt2);

  @Test
  void pasaCuandoAmbosCriteriosSeCumplen() {
    when(crt1.satisfaceCriterio(catalogo)).thenReturn(true);
    when(crt2.satisfaceCriterio(catalogo)).thenReturn(true);

    assertTrue(or.satisfaceCriterio(catalogo));
    verify(crt1).satisfaceCriterio(catalogo);
    verifyNoInteractions(crt2); //showrtcircuitttttt.
  }

  @Test
  void pasaCuandoElPrimeroNoSeCumple() {
    when(crt1.satisfaceCriterio(catalogo)).thenReturn(false);
    when(crt2.satisfaceCriterio(catalogo)).thenReturn(true);

    assertTrue(or.satisfaceCriterio(catalogo));
    verify(crt1).satisfaceCriterio(catalogo);
    verify(crt2).satisfaceCriterio(catalogo);
  }

  @Test
  void pasaCuandoSoloElSegundoNoSeCumple() {
    when(crt1.satisfaceCriterio(catalogo)).thenReturn(true);
    when(crt2.satisfaceCriterio(catalogo)).thenReturn(false);

    assertTrue(or.satisfaceCriterio(catalogo));
    verify(crt1).satisfaceCriterio(catalogo);
    verifyNoInteractions(crt2); // ya dio true por shotcircuit
  }

  @Test
  void rebotaCuandoAmbosCriteriosNOSeCumplen() {
    when(crt1.satisfaceCriterio(catalogo)).thenReturn(false);
    when(crt2.satisfaceCriterio(catalogo)).thenReturn(false);

    assertFalse(or.satisfaceCriterio(catalogo));
    verify(crt1).satisfaceCriterio(catalogo);
    verify(crt2).satisfaceCriterio(catalogo);
  }
}