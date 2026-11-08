package org.viajesOnline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.Cupo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaqueteTest {
  private Paquete paquete = new Paquete(
          "Paquete de prueba",
          "Descripción",
          100.0,
          mock(Cupo.class)
  );

  Catalogo item1 = mock(Catalogo.class);
  Catalogo item2 = mock(Paquete.class);
  Catalogo item3 = mock(ServicioIndividual.class);

  @BeforeEach
  void setUp() {
    when(item1.getPrecioBase()).thenReturn(10.50);
    when(item2.getPrecioBase()).thenReturn(20.00);
    when(item3.getPrecioBase()).thenReturn(4.25);
  }

  @Test
  void noTieneItems() {
    assertTrue(paquete.verItems().isEmpty());
  }

  @Test
  void agregarUno() {
    Catalogo item = mock(Catalogo.class);
    paquete.agregarItem(item);
    assertEquals(1, paquete.verItems().size());
    assertSame(item, paquete.verItems().getFirst());
  }

  @Test
  void modificarLaListaDevueltaNoAfectaAlPaquete() {
    paquete.agregarItem(item1);

    List<Catalogo> items = paquete.verItems();

    assertThrows(
            UnsupportedOperationException.class,
            () -> items.remove(item1)
    );
    assertEquals(1, paquete.verItems().size());
    assertTrue(paquete.verItems().contains(item1));
  }

  @Test
  void precioBaseEsLaSumaDeLosPreciosDeLosItems() {
    paquete.agregarItem(item1);
    paquete.agregarItem(item2);
    paquete.agregarItem(item3);
    assertEquals(34.75, paquete.getPrecioBase());
  }

  @Test
  void precioFinalEsCeroCuandoNoHayItems() {
    assertEquals(0.0, paquete.precioFinal());
  }

  @Test
  void seLeAplicaUnDescuentoALaSumaDeTodos(){
    paquete.agregarItem(item1);
    paquete.agregarItem(item2);
    paquete.agregarItem(item3);
    paquete.darDescuento(10);
    assertEquals(31.275, paquete.precioFinal());
  }


}