package org.viajesOnline;

import org.junit.jupiter.api.Test;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CatalogoTest {

  private static class TestCatalogo extends Catalogo { //le subi categoria a catalogo
    TestCatalogo(String nombre, String descripcion, double precio, Cupo cupo, Periodo periodo, Categoria categoria) {
      super(nombre, descripcion, precio, cupo, periodo, categoria);
    }
  }

  Categoria cat = Categoria.ALOJAMIENTO;
  Cupo cupo = mock(Cupo.class);
  Periodo periodoMock = mock(Periodo.class);
  Catalogo catalogo = new TestCatalogo("Hotel", "Descripción", 150.0, new Cupo(10, 10), new Periodo( LocalDate.of(2026, 6, 1),LocalDate.of(2026, 6, 10)), cat);

  @Test
  void GetterNombreEsCorrecto(){
    assertEquals("Hotel", catalogo.getNombre());
  }

  @Test
  void GetterDescripcionEsCorrecto(){
    assertEquals("Descripción", catalogo.getDescripcion());
  }

  @Test
  void getterCategoriaEsCorrecto(){
    assertEquals(Categoria.ALOJAMIENTO, catalogo.categoria());
  }

  @Test
  void GetterCupoEsCorrecto(){
    Cupo c1 = new Cupo(10, 10);

    assertEquals(c1.getCupoInicial(), catalogo.getCupo().getCupoInicial());
    assertEquals(c1.getCupoDisponible(), catalogo.getCupo().getCupoDisponible());
    assertEquals(c1.getCupoReservado(), catalogo.getCupo().getCupoReservado());
  }

  @Test
  void GetterPeriodoEsCorrecto(){
    assertEquals(new Periodo( LocalDate.of(2026, 6, 1),LocalDate.of(2026, 6, 10)), catalogo.getPeriodoVigencia());
  }

  @Test
  void lanzaCompletarCamposSiNombreEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo(null, "Descripción", 100.0, cupo, periodoMock, cat)
    );
  }

  @Test
  void lanzaCompletarCamposSiDescripcionEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Curso", null, 100.0, cupo, periodoMock, cat)
    );
  }

  @Test
  void lanzaCompletarCamposSiCupoEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Curso", "Descripción", 100.0, null,  periodoMock,cat)
    );
  }

  @Test
  void lanzaCompletarCamposSiPeriodoEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("khjdfjhj", "Descripción", 100.0, cupo, null, cat)
    );
  }

  @Test
  void lanzaCompletarCamposSiCategoriaEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("khjdfjhj", "Descripción", 100.0, cupo, periodoMock, null)
    );
  }

  @Test
  void rechazaSiPrecioEsNegativoOCero() {
    assertThrows(CompletarCampos.class,
            () -> new TestCatalogo("Hotel", "Descripción", 0.0, cupo, periodoMock, cat));
    assertThrows(CompletarCampos.class,
                    () -> new TestCatalogo("Hotel", "Descripción", -1.0, cupo, periodoMock, cat));
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

  @Test
  void pasaSiEstaContenidoEnPeriodoVigente() {
    Periodo solicitado = new Periodo(
            LocalDate.of(2026, 6, 5),
            LocalDate.of(2026, 6, 12));

    assertTrue(catalogo.estaDisponibleEn(solicitado));
  }

  @Test
  void rebotaPeriodoSiNoHayCupo() {
    Catalogo cat1 = new TestCatalogo("Hotel", "Descripción", 150.0, new Cupo(10, 0), periodoMock, cat);
    assertFalse(cat1.estaDisponibleEn(new Periodo(LocalDate.of(2026, 6, 5), LocalDate.of(2026, 6, 12))));
    //shortcircuit tiene que actuar primero.
  }

  @Test
  void rebotaSiLosPeriodosNoSeSuperponen() {
    Catalogo cat2 = new TestCatalogo("Hotel", "Descripción", 150.0, new Cupo(10, 10),
            new Periodo( LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 10)), cat);

    assertFalse(cat2.estaDisponibleEn(new Periodo(LocalDate.of(2026, 6, 11), LocalDate.of(2026, 6, 15))));
  }
}