package org.viajesOnline.reporte;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.items.Item;
import org.viajesOnline.criterios.Criterio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CatalogoTest {
  private Catalogo empresa;
  private Criterio criterio = mock(Criterio.class);
  private Item item1 = mock(Item.class);
  private Item item2 = mock(Item.class);

  @BeforeEach
  void setUp(){
    empresa = Catalogo.getInstance();
    empresa.addItem(item1);
    empresa.addItem(item2);
  }

  @AfterEach
  void reset(){
    empresa.borrarTodo();
  }

  @Test
  void buscarEnCatalogoDevuelveSoloLosElementosQueCumplenElCriterio() {
    when(criterio.satisfaceCriterio(item1)).thenReturn(true);
    when(criterio.satisfaceCriterio(item2)).thenReturn(false);

    List<Item> resultado = empresa.buscarEnCatalogo(criterio);

    assertEquals(List.of(item1), resultado);
    verify(criterio).satisfaceCriterio(item1);
    verify(criterio).satisfaceCriterio(item2);
  }

  @Test
  void getItemsDevuelveUnaCopiaDeLaLista() {
    assertThrows(UnsupportedOperationException.class,
              () -> empresa.getItems().add(mock(Item.class)));
  }

    @Test
    void delItemEliminaElElemento() {
      empresa.delItem(item1);
      empresa.delItem(item2);
      assertTrue(empresa.getItems().isEmpty());
    }
  }
