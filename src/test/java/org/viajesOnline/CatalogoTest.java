package org.viajesOnline;

import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CatalogoTest {
  private static class TestCatalogo extends Catalogo {
    TestCatalogo(String nombre, String descripcion, double precio, Cupo cupo) {
      super(nombre, descripcion, precio, cupo);
    }
  }
  Cupo cupo = mock(Cupo.class);
  Catalogo catalogo = new TestCatalogo("Hotel", "Descripción", 150.0, new Cupo(10));

  @Test
  void GetterNombreEsCorrecto(){
    assertEquals("Hotel", catalogo.getNombre());
  }

  @Test
  void GetterDescripcionEsCorrecto(){
    assertEquals("Descripción", catalogo.getDescripcion());
  }

  @Test
  void lanzaCompletarCamposSiNombreEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo(null, "Descripción", 100.0, cupo)
    );
  }

  @Test
  void lanzaCompletarCamposSiDescripcionEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Hotel", null, 100.0, cupo)
    );
  }

  @Test
  void lanzaCompletarCamposSiCupoEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Hotel", "Descripción", 100.0, null)
    );
  }

  @Test
  void rechazaSiPrecioEsNegativoOCero() {
    assertThrows(CompletarCampos.class,
            () -> new TestCatalogo("Hotel", "Descripción", 0.0, cupo));
    assertThrows(CompletarCampos.class,
                    () -> new TestCatalogo("Hotel", "Descripción", -1.0, cupo));
  }

  @Test
  void precioFinalAplicaElDescuento() {
    catalogo.darDescuento(20);
    assertEquals(120.0, catalogo.precioFinal());
  }

  @Test
  void precioFinalSinDescuentoDevuelvePrecioBase() {
    assertEquals(catalogo.getPrecioBase(), catalogo.precioFinal());
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
  void incrementarCupoAumentaElCupoDisponible() {
    catalogo.incrementarCupo();
    assertEquals(11, catalogo.getCupoDisponible());
  }

  @Test
  void decrementarReduceElCupoDisponible() {
    catalogo.decrementarCupo();
    assertEquals(9, catalogo.getCupoDisponible());
  }
}