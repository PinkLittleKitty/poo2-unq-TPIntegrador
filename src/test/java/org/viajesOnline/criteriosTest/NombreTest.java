package org.viajesOnline.criteriosTest;
import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Item;
import org.viajesOnline.criterios.Nombre;
import org.viajesOnline.exceptions.CompletarCampos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NombreTest {
  Nombre criterio = new Nombre("caribe");
  Item item = mock(Item.class);

  @Test
  void satisfaceCriterioCuandoElNombreCoincide() {
    when(item.getNombre()).thenReturn("caribe");
    assertTrue(criterio.satisfaceCriterio(item));
  }

  @Test
  void satisfaceCriterioCuandoCoincideSinImportarMayus() {
    when(item.getNombre()).thenReturn("CArIbE");
    assertTrue(criterio.satisfaceCriterio(item));
  }

  @Test
  void noSatisfaceCriterioCuandoElNombreNoCoincide() {
    when(item.getNombre()).thenReturn("otraCosa");
    assertFalse(criterio.satisfaceCriterio(item));
  }

  @Test
  void constructorRechazaNombreNuloOVacio() {
    assertThrows(CompletarCampos.class, () -> new Nombre(null));
    assertThrows(CompletarCampos.class, () -> new Nombre(""));
    assertThrows(CompletarCampos.class, () -> new Nombre("   "));
  }
}