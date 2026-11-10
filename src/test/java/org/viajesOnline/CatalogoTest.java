package org.viajesOnline;

import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class CatalogoTest {
  private static class TestCatalogo extends Catalogo {
    TestCatalogo(String nombre, String descripcion, double precio, Cupo cupo, Periodo periodo) {
      super(nombre, descripcion, precio, cupo, periodo);
    }
  }
  Cupo cupo = mock(Cupo.class);
  Periodo periodoMock = mock(Periodo.class);
  Catalogo catalogo = new TestCatalogo("Hotel", "Descripción", 150.0, new Cupo(10, 10), periodoMock);

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
            () -> new TestCatalogo(null, "Descripción", 100.0, cupo, periodoMock)
    );
  }

  @Test
  void lanzaCompletarCamposSiDescripcionEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Curso", null, 100.0, cupo, periodoMock)
    );
  }

  @Test
  void lanzaCompletarCamposSiCupoEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Curso", "Descripción", 100.0, null,  periodoMock)
    );
  }

  @Test
  void rechazaSiPrecioEsNegativoOCero() {
    assertAll(
            () -> assertThrows(CompletarCampos.class,
                    () -> new TestCatalogo("Curso", "Descripción", 0.0, cupo, periodoMock)),
            () -> assertThrows(CompletarCampos.class,
                    () -> new TestCatalogo("Curso", "Descripción", -1.0, cupo, periodoMock))
    );
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