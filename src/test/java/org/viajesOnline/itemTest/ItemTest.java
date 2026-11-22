package org.viajesOnline.itemTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.items.Item;
import org.viajesOnline.items.Categoria;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.CalendarioCupos;
import org.viajesOnline.utils.Periodo;

import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemTest {

  private static class TestCatalogo extends Item { //le subi categoria a catalogo
    TestCatalogo(String nombre, String descripcion, int cupo, Periodo periodo, Categoria categoria, String proveedor) {
      super(nombre, descripcion, cupo, periodo, categoria, proveedor);
    }

    @Override
    public double getPrecioBase() {
      return 150.0;
    }
  }

  Categoria cat = Categoria.ALOJAMIENTO;
  int cupoInicial = 10;
  Periodo periodoVigente = new Periodo(LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 10));
  Item item = new TestCatalogo("Hotel", "Descripción", cupoInicial, periodoVigente, cat, "ProveedorA");

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
    assertEquals(cupoInicial, item.getCupo().getCupoDisponible());
  }

  @Test
  void GetterPeriodoEsCorrecto(){
    //2026/06/1 a 2026/06/10
    assertEquals(periodoVigente, item.getPeriodoVigencia());
  }

  @Test
  void getCupoPorDiaInicial() {
    CalendarioCupos calendario = new CalendarioCupos(periodoVigente, 10);
    assertEquals(10, calendario.getCupoPorDiaInicial());
  }

  @Test
  void lanzaCompletarCamposSiNombreEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo(null, "Descripción", cupoInicial, periodoVigente, cat, "ProvvedorA")
    );
  }

  @Test
  void lanzaCompletarCamposSiDescripcionEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Hotel", null, cupoInicial, periodoVigente, cat, "ProvvedorA")
    );
  }

  @Test
  void lanzaCompletarCamposSiPeriodoEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Hotel", "Descripción", cupoInicial, null, cat, "ProvvedorA")
    );
  }

  @Test
  void lanzaCompletarCamposSiCategoriaEsNull() {
    assertThrows(
            CompletarCampos.class,
            () -> new TestCatalogo("Hotel", "Descripción", cupoInicial, periodoVigente, null, "ProvvedorA")
    );
  }

  @Test
  void rechazaProveedorNulo() {
    assertThrows(CompletarCampos.class, () ->
            new TestCatalogo(
                    "Hotel",
                    "Descripción",
                    cupoInicial,
                    periodoVigente,
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
                    cupoInicial,
                    periodoVigente,
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
    item.incrementarCupo(periodoVigente);
    assertEquals(11, item.getCupo().getCupoDisponible());
  }

  @Test
  void decrementarReduceElCupoDisponible() {
    item.decrementarCupo(periodoVigente);
    assertEquals(9, item.getCupo().getCupoDisponible());
  }

  @Test
  void pasaSiTieneCupoYEstaContenidoEnPeriodoVigente() {
    //catalogo periodo = 2026/06/1 a 2026/06/10
    Periodo solicitado = new Periodo(LocalDate.of(2026, 6, 2), LocalDate.of(2026, 6, 5));

    assertTrue(item.estaDisponibleEn(solicitado));
  }

  @Test
  void rebotaPeriodoSiNoHayCupo() {
    Item itemSinCupo = new TestCatalogo("Hotel", "Descripción", 0, periodoVigente, cat, "ProveedorA");
    assertFalse(itemSinCupo.estaDisponibleEn(periodoVigente));
  }

  @Test
  void rebotaSiTieneCupoYLosPeriodosNoSeSuperponen() {
    Periodo fueraDeVigencia = new Periodo(LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 5));
    assertFalse(item.estaDisponibleEn(fueraDeVigencia));
  }
}