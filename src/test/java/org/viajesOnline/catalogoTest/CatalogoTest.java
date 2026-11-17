package org.viajesOnline.catalogoTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Catalogo;
import org.viajesOnline.catalogo.Categoria;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CatalogoTest {

  private static class TestCatalogo extends Catalogo { //le subi categoria a catalogo
    TestCatalogo(String nombre, String descripcion, double precio, Cupo cupo, Periodo periodo, Categoria categoria, String proveedor) {
      super(nombre, descripcion, precio, cupo, periodo, categoria, proveedor);
    }
  }

  Categoria cat = Categoria.ALOJAMIENTO;
  Cupo cupo = mock(Cupo.class);
  Periodo periodoMock = mock(Periodo.class);
  Catalogo catalogo = new TestCatalogo("Hotel", "Descripción", 150.0, cupo, periodoMock, cat, "ProveedorA");

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
  void getterProveedorEsCorrecto(){
    assertEquals("ProveedorA", catalogo.proveedor());
  }

  @Test
  void GetterCupoEsCorrecto(){
    assertEquals(cupo, catalogo.getCupo());
  }

  @Test
  void GetterPeriodoEsCorrecto(){
    //2026/06/1 a 2026/06/10
    assertEquals(periodoMock, catalogo.getPeriodoVigencia());
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
    verify(cupo).incrementarCupo();
  }

  @Test
  void decrementarReduceElCupoDisponible() {
    catalogo.decrementarCupo();
    verify(cupo).decrementarCupo();
  }

  @Test
  void pasaSiTieneCupoYEstaContenidoEnPeriodoVigente() {
    //catalogo periodo = 2026/06/1 a 2026/06/10
    Periodo solicitado = mock(Periodo.class);

    when(cupo.getCupoDisponible()).thenReturn(10);
    when(periodoMock.seSuperponeCon(solicitado)).thenReturn(true);

    assertTrue(catalogo.estaDisponibleEn(solicitado));
  }

  @Test
  void rebotaPeriodoSiNoHayCupo() {
    when(cupo.getCupoDisponible()).thenReturn(0);
    assertFalse(catalogo.estaDisponibleEn(periodoMock));
    //shortcircuit tiene que actuar primero.
  }

  @Test
  void rebotaSiTieneCupoYLosPeriodosNoSeSuperponen() {
    //catalogo periodo = 2026/06/1 a 2026/06/10; cupo 10.
    Periodo solicitado = mock(Periodo.class);

    when(cupo.getCupoDisponible()).thenReturn(10);
    when(periodoMock.seSuperponeCon(solicitado)).thenReturn(false);
    assertFalse(catalogo.estaDisponibleEn(solicitado));
  }
}