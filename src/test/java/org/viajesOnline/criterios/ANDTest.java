package org.viajesOnline.criterios;

import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;

import java.util.List;

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
    when(crt1.sastifaceCriterio(catalogo)).thenReturn(true);
    when(crt2.sastifaceCriterio(catalogo)).thenReturn(true);

    assertTrue(and.sastifaceCriterio(catalogo));
    verify(crt1).sastifaceCriterio(catalogo);
    verify(crt2).sastifaceCriterio(catalogo);
  }

  @Test
  void rebotaCuandoElPrimeroNoSeCumple() {
    when(crt1.sastifaceCriterio(catalogo)).thenReturn(false);

    assertFalse(and.sastifaceCriterio(catalogo));
    verify(crt1).sastifaceCriterio(catalogo);
    verifyNoInteractions(crt2); // veo si funciona el shortcircuit.
  }

  @Test
  void rebotaCuandoSoloElSegundoNoSeCumple() {

    when(crt1.sastifaceCriterio(catalogo)).thenReturn(true);
    when(crt2.sastifaceCriterio(catalogo)).thenReturn(false);

    assertFalse(and.sastifaceCriterio(catalogo));
    verify(crt1).sastifaceCriterio(catalogo);
    verify(crt2).sastifaceCriterio(catalogo);
  }
//
//  @Test
//  void busquedaFiltraLosCatalogosPorNombre() {
//    Busqueda busqueda = new Busqueda();
//    Catalogo coincide = mock(Catalogo.class);
//    Catalogo noCoincide = mock(Catalogo.class);
//
//    when(coincide.getNombre()).thenReturn("harry potter");
//    when(noCoincide.getNombre()).thenReturn("hermione granger");
//
//    busqueda.addItem(coincide);
//    busqueda.addItem(noCoincide);
//
//    List<Catalogo> resultado = busqueda.buscarEnCatalogo(criterio);
//
//    assertEquals(List.of(coincide), resultado);
//  }
}