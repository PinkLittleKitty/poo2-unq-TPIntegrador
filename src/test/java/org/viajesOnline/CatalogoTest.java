package org.viajesOnline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoTest {
  private static class TestCatalogo extends Catalogo {
    TestCatalogo(String nombre, String descripcion, double precio, int cupo) {
      super(nombre, descripcion, precio, cupo);
    }
  }
  Catalogo catalogo = new TestCatalogo("Hotel", "Descripción", 150.0, 10);

  @Test
  void precioFinalAplicaElDescuentoPorcentual() {
    catalogo.darDescuento(20);
    assertEquals(120.0, catalogo.precioFinal());
  }

  @Test
  void precioFinalSinDescuentoDevuelvePrecioBase() {
    assertEquals(150.0, catalogo.precioFinal());
  }

  @Test
  void precioFinalConDescuentoCompletoDevuelveCero() {
    catalogo.darDescuento(100);
    assertEquals(0.0, catalogo.precioFinal());
  }

  @Test
  void rechazaDescuentoNegativo() {
    assertThrows(IllegalArgumentException.class,
            () -> catalogo.darDescuento(-1));
  }

  @Test
  void rechazaDescuentoMayorQueCien() {

    assertThrows(IllegalArgumentException.class,
            () -> catalogo.darDescuento(101));
  }

  @Test
  void recuperarCupoAumentaElCupoDisponible() {
    catalogo.recuperarCupo(3);

    assertEquals(13, catalogo.getCupoDisponible());
  }

  @Test
  void liberarCupoReduceElCupoDisponible() {

    catalogo.liberarCupo(3);

    assertEquals(7, catalogo.getCupoDisponible());

  }
}