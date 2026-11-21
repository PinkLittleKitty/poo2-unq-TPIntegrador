package org.viajesOnline.criteriosTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Item;
import org.viajesOnline.criterios.Criterio;
import org.viajesOnline.criterios.NOT;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NOTTest {
  private Criterio crt = mock(Criterio.class);
  private Item item = mock(Item.class);
  private NOT criterio = new NOT(crt);

  @Test
  void satisfaceCriterioCuandoCoincide() { // opuesto del criterio elegido
    when(crt.satisfaceCriterio(item)).thenReturn(false);
    assertTrue(criterio.satisfaceCriterio(item));
  }

  @Test
  void noSatisfaceCriterioCuandoNoCoincide() {
    when(crt.satisfaceCriterio(item)).thenReturn(true);
    assertFalse(criterio.satisfaceCriterio(item));
  }

}