package org.viajesOnline.criteriosTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Item;
import org.viajesOnline.criterios.AND;
import org.viajesOnline.criterios.Criterio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ANDTest {
  Criterio crt1 = mock(Criterio.class);
  Criterio crt2 = mock(Criterio.class);
  Item item = mock(Item.class);
  AND and = new AND(crt1, crt2);

  @Test
  void pasaCuandoAmbosCriteriosSeCumplen() {
    when(crt1.satisfaceCriterio(item)).thenReturn(true);
    when(crt2.satisfaceCriterio(item)).thenReturn(true);

    assertTrue(and.satisfaceCriterio(item));
    verify(crt1).satisfaceCriterio(item);
    verify(crt2).satisfaceCriterio(item);
  }

  @Test
  void rebotaCuandoElPrimeroNoSeCumple() {
    when(crt1.satisfaceCriterio(item)).thenReturn(false);

    assertFalse(and.satisfaceCriterio(item));
    verify(crt1).satisfaceCriterio(item);
    verifyNoInteractions(crt2); // veo si funciona el shortcircuit.
  }

  @Test
  void rebotaCuandoSoloElSegundoNoSeCumple() {

    when(crt1.satisfaceCriterio(item)).thenReturn(true);
    when(crt2.satisfaceCriterio(item)).thenReturn(false);

    assertFalse(and.satisfaceCriterio(item));
    verify(crt1).satisfaceCriterio(item);
    verify(crt2).satisfaceCriterio(item);
  }
}