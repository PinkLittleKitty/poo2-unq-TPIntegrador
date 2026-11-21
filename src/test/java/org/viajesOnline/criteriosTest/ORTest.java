package org.viajesOnline.criteriosTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Item;
import org.viajesOnline.criterios.Criterio;
import org.viajesOnline.criterios.OR;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ORTest {
  Criterio crt1 = mock(Criterio.class);
  Criterio crt2 = mock(Criterio.class);
  Item item = mock(Item.class);
  OR or = new OR(crt1, crt2);

  @Test
  void pasaCuandoAmbosCriteriosSeCumplen() {
    when(crt1.satisfaceCriterio(item)).thenReturn(true);
    when(crt2.satisfaceCriterio(item)).thenReturn(true);

    assertTrue(or.satisfaceCriterio(item));
    verify(crt1).satisfaceCriterio(item);
    verifyNoInteractions(crt2); //showrtcircuitttttt.
  }

  @Test
  void pasaCuandoElPrimeroNoSeCumple() {
    when(crt1.satisfaceCriterio(item)).thenReturn(false);
    when(crt2.satisfaceCriterio(item)).thenReturn(true);

    assertTrue(or.satisfaceCriterio(item));
    verify(crt1).satisfaceCriterio(item);
    verify(crt2).satisfaceCriterio(item);
  }

  @Test
  void pasaCuandoSoloElSegundoNoSeCumple() {
    when(crt1.satisfaceCriterio(item)).thenReturn(true);
    when(crt2.satisfaceCriterio(item)).thenReturn(false);

    assertTrue(or.satisfaceCriterio(item));
    verify(crt1).satisfaceCriterio(item);
    verifyNoInteractions(crt2); // ya dio true por shotcircuit
  }

  @Test
  void rebotaCuandoAmbosCriteriosNOSeCumplen() {
    when(crt1.satisfaceCriterio(item)).thenReturn(false);
    when(crt2.satisfaceCriterio(item)).thenReturn(false);

    assertFalse(or.satisfaceCriterio(item));
    verify(crt1).satisfaceCriterio(item);
    verify(crt2).satisfaceCriterio(item);
  }
}