package org.viajesOnline.criterios;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.exceptions.CompletarCampos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NombreTest {
  Nombre criterio = new Nombre("caribe");
  Catalogo catalogo = mock(Catalogo.class);

  @Test
  void satisfaceCriterioCuandoElNombreCoincide() {
    when(catalogo.getNombre()).thenReturn("caribe");
    assertTrue(criterio.satisfaceCriterio(catalogo));
  }

  @Test
  void satisfaceCriterioCuandoCoincideSinImportarMayus() {
    when(catalogo.getNombre()).thenReturn("CArIbE");
    assertTrue(criterio.satisfaceCriterio(catalogo));
  }

  @Test
  void noSatisfaceCriterioCuandoElNombreNoCoincide() {
    when(catalogo.getNombre()).thenReturn("otraCosa");
    assertFalse(criterio.satisfaceCriterio(catalogo));
  }

  @Test
  void constructorRechazaNombreNuloOVacio() {
    assertThrows(CompletarCampos.class, () -> new Nombre(null));
    assertThrows(CompletarCampos.class, () -> new Nombre(""));
    assertThrows(CompletarCampos.class, () -> new Nombre("   "));
  }
}