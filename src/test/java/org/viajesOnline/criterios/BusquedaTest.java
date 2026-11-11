package org.viajesOnline.criterios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BusquedaTest {
  private Busqueda busqueda = new Busqueda();
  private Criterio criterio = mock(Criterio.class);
  private Catalogo item1 = mock(Catalogo.class);
  private Catalogo item2 = mock(Catalogo.class);

  @BeforeEach
  void setUp(){
    busqueda.addItem(item1);
    busqueda.addItem(item2);
  }

  @Test
  void buscarEnCatalogoDevuelveSoloLosElementosQueCumplenElCriterio() {
    when(criterio.satisfaceCriterio(item1)).thenReturn(true);
    when(criterio.satisfaceCriterio(item2)).thenReturn(false);

    List<Catalogo> resultado = busqueda.buscarEnCatalogo(criterio);

    assertEquals(List.of(item1), resultado);
    verify(criterio).satisfaceCriterio(item1);
    verify(criterio).satisfaceCriterio(item2);
  }

  @Test
  void getItemsDevuelveUnaCopiaDeLaLista() {
    List<Catalogo> copia_item = busqueda.getItems();

    assertEquals(List.of(item1,item2), copia_item);
    assertThrows(UnsupportedOperationException.class,
              () -> copia_item.add(mock(Catalogo.class)));
  }

    @Test
    void delItemEliminaElElemento() {
      busqueda.delItem(item1);
      busqueda.delItem(item2);
      assertTrue(busqueda.getItems().isEmpty());
    }
  }
