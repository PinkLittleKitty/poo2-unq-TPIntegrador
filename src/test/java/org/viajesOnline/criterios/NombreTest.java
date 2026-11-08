package org.viajesOnline.criterios;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.exceptions.CompletarCampos;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NombreTest {
  Nombre criterio = new Nombre("harry potter");
  Catalogo catalogo = mock(Catalogo.class);

  @Test
  void satisfaceCriterioCuandoElNombreCoincide() {
    when(catalogo.getNombre()).thenReturn("harry potter");
    assertTrue(criterio.sastifaceCriterio(catalogo));
  }

  @Test
  void noSatisfaceCriterioCuandoElNombreNoCoincide() {
    when(catalogo.getNombre()).thenReturn("hermione granger");
    assertFalse(criterio.sastifaceCriterio(catalogo));
  }

  @Test
  void constructorRechazaNombreNuloOVacio() {
    assertThrows(CompletarCampos.class, () -> new Nombre(null));
    assertThrows(CompletarCampos.class, () -> new Nombre(""));
    assertThrows(CompletarCampos.class, () -> new Nombre("   "));
  }

  @Test
  void busquedaFiltraLosCatalogosPorNombre() {
    Busqueda busqueda = new Busqueda();
    Catalogo coincide = mock(Catalogo.class);
    Catalogo noCoincide = mock(Catalogo.class);

    when(coincide.getNombre()).thenReturn("harry potter");
    when(noCoincide.getNombre()).thenReturn("hermione granger");

    busqueda.addItem(coincide);
    busqueda.addItem(noCoincide);

    List<Catalogo> resultado = busqueda.buscarEnCatalogo(criterio);

    assertEquals(List.of(coincide), resultado);
  }
}