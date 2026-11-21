package org.viajesOnline.catalogoTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Item;
import org.viajesOnline.catalogo.Categoria;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemTest {

  private static class TestCatalogo extends Item { //le subi categoria a catalogo
    TestCatalogo(String nombre, String descripcion, double precio, Cupo cupo, Periodo periodo, Categoria categoria, String proveedor) {
      super(nombre, descripcion, precio, cupo, periodo, categoria, proveedor);
    }
  }

  Categoria cat = Categoria.ALOJAMIENTO;
  Cupo cupo = mock(Cupo.class);
  Periodo periodoMock = mock(Periodo.class);
  Item item = new TestCatalogo("Hotel", "Descripción", 150.0, cupo, periodoMock, cat, "ProveedorA");

  @Test
  void GetterNombreEsCorrecto(){
    assertEquals("Hotel", item.getNombre());
  }

  @Test
  void GetterDescripcionEsCorrecto(){
    assertEquals("Descripción", item.getDescripcion());
  }

  @Test
  void getterCategoriaEsCorrecto(){
    assertEquals(Categoria.ALOJAMIENTO, item.categoria());
  }

  @Test
  void getterProveedorEsCorrecto(){
    assertEquals("ProveedorA", item.proveedor());
  }

  @Test
  void GetterCupoEsCorrecto(){
    assertEquals(cupo, item.getCupo());
  }

  @Test
  void GetterPeriodoEsCorrecto(){
    //2026/06/1 a 2026/06/10
    assertEquals(periodoMock, item.getPeriodoVigencia());
  }

  @Test
  void lanzaCompletarCamposSiNombreEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo(null, "Descripción", 100.0, cupo, periodoMock, cat, "ProvvedorA")
    );
  }

  @Test
  void lanzaCompletarCamposSiDescripcionEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("dfgdfgdfg", null, 100.0, cupo, periodoMock, cat, "ProvvedorA")
    );
  }

  @Test
  void lanzaCompletarCamposSiCupoEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("asdfaefgh", "Descripción", 100.0, null,  periodoMock,cat, "ProvvedorA")
    );
  }

  @Test
  void lanzaCompletarCamposSiPeriodoEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("khjdfjhj", "Descripción", 100.0, cupo, null, cat, "ProvvedorA")
    );
  }

  @Test
  void lanzaCompletarCamposSiCategoriaEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("khjdfjhj", "Descripción", 100.0, cupo, periodoMock, null, "ProvvedorA")
    );
  }

  @Test
  void rechazaSiPrecioEsNegativoOCero() {
    assertThrows(CompletarCampos.class,
            () -> new TestCatalogo("Hotel", "Descripción", 0.0, cupo, periodoMock, cat, "ProvvedorA"));
    assertThrows(CompletarCampos.class,
                    () -> new TestCatalogo("Hotel", "Descripción", -1.0, cupo, periodoMock, cat, "ProvvedorA"));
  }

  @Test
  void rechazaProveedorNulo() {
    assertThrows(CompletarCampos.class, () ->
            new TestCatalogo(
                    "Hotel",
                    "Descripción",
                    12.3,
                    cupo,
                    periodoMock,
                    cat,
                    null
            )
    );
  }

  @Test
  void rechazaProveedorVacio() {
    assertThrows(CompletarCampos.class, () ->
            new TestCatalogo(
                    "Hotel",
                    "Descripción",
                    12.3,
                    cupo,
                    periodoMock,
                    cat,
                    ""
            ));
  }
    @Test
  void precioFinalAplicaElDescuento() {
    item.darDescuento(20);
    assertEquals(120.0, item.precioFinal());
  }

  @Test
  void precioFinalSinDescuentoDevuelvePrecioBase() {
    assertEquals(item.getPrecioBase(), item.precioFinal());
  }

  @Test
  void precioFinalConDescuentoCompletoDevuelveCero() {
    item.darDescuento(100);
    assertEquals(0.0, item.precioFinal());
  }

  @Test
  void rechazaDescuentoNegativo() {
    assertThrows(IllegalArgumentException.class,
            () -> item.darDescuento(-1));
  }

  @Test
  void rechazaDescuentoMayorQueCien() {
    assertThrows(IllegalArgumentException.class,
            () -> item.darDescuento(101));
  }

  @Test
  void incrementarCupoAumentaElCupoDisponible() {
    item.incrementarCupo();
    verify(cupo).incrementarCupo();
  }

  @Test
  void decrementarReduceElCupoDisponible() {
    item.decrementarCupo();
    verify(cupo).decrementarCupo();
  }

  @Test
  void pasaSiTieneCupoYEstaContenidoEnPeriodoVigente() {
    //catalogo periodo = 2026/06/1 a 2026/06/10
    Periodo solicitado = mock(Periodo.class);

    when(cupo.getCupoDisponible()).thenReturn(10);
    when(periodoMock.seSuperponeCon(solicitado)).thenReturn(true);

    assertTrue(item.estaDisponibleEn(solicitado));
  }

  @Test
  void rebotaPeriodoSiNoHayCupo() {
    when(cupo.getCupoDisponible()).thenReturn(0);
    assertFalse(item.estaDisponibleEn(periodoMock));
    //shortcircuit tiene que actuar primero.
  }

  @Test
  void rebotaSiTieneCupoYLosPeriodosNoSeSuperponen() {
    //catalogo periodo = 2026/06/1 a 2026/06/10; cupo 10.
    Periodo solicitado = mock(Periodo.class);

    when(cupo.getCupoDisponible()).thenReturn(10);
    when(periodoMock.seSuperponeCon(solicitado)).thenReturn(false);
    assertFalse(item.estaDisponibleEn(solicitado));
  }
}