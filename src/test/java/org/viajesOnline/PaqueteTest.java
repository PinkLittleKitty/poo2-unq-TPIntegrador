package org.viajesOnline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.Cupo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaqueteTest {
  private Paquete paquete;

  @BeforeEach
  void setUp() {
    paquete = new Paquete(
            "Paquete de prueba",
            "Descripción",
            100.0,
            mock(Cupo.class)
    );
  }

  @Test
  void verItemsInicialmenteEstaVacio() {
    assertTrue(paquete.verItems().isEmpty());
  }

  @Test
  void agregarItemLoIncluyeEnLaLista() {
    Catalogo item = mock(Catalogo.class);
    paquete.agregarItem(item);
    assertEquals(1, paquete.verItems().size());
    assertSame(item, paquete.verItems().get(0));
  }

  @Test
  void verItemsDevuelveUnaListaNoModificable() {
    paquete.agregarItem(mock(Catalogo.class));
    List<Catalogo> items = paquete.verItems();
    assertThrows(
            UnsupportedOperationException.class,
            () -> items.add(mock(Catalogo.class))
    );
  }

  @Test
  void modificarLaListaDevueltaNoAfectaAlPaquete() {
    Catalogo item = mock(Catalogo.class);
    paquete.agregarItem(item);

    List<Catalogo> items = paquete.verItems();

    assertThrows(
            UnsupportedOperationException.class,
            () -> items.remove(item)
    );
    assertEquals(1, paquete.verItems().size());
  }

  @Test
  void precioBaseEsLaSumaDeLosPreciosDeLosItems() {
    Catalogo item1 = mock(Catalogo.class);
    Catalogo item2 = mock(Paquete.class);
    Catalogo item3 = mock(ServicioIndividual.class);

    when(item1.getPrecioBase()).thenReturn(10.50);
    when(item2.getPrecioBase()).thenReturn(20.00);
    when(item3.getPrecioBase()).thenReturn(4.25);

    paquete.agregarItem(item1);
    paquete.agregarItem(item2);
    paquete.agregarItem(item3);

    assertEquals(34.75, paquete.getPrecioBase());
  }

  @Test
  void precioBaseEsCeroCuandoNoHayItems() {
    assertEquals(0.0, paquete.getPrecioBase());
  }
}