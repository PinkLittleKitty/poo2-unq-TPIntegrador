package org.viajesOnline;

import org.junit.jupiter.api.Test;
import org.viajesOnline.atributos.DefinicionAtributo;
import org.viajesOnline.atributos.Numerico;
import org.viajesOnline.criterios.Nombre;
import org.viajesOnline.exceptions.CompletarCampos;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ServicioIndividualTest {
  private Categoria cat = Categoria.ALOJAMIENTO;
  private ServicioIndividual servicio = new ServicioIndividual(
          "Hotel",
          "Descripción",
          new Cupo(2, 2),
          123,
          "ProveedorA",
          150.0,
          cat,
          mock(Periodo.class)
  );
  private DefinicionAtributo attDinamico = mock(DefinicionAtributo.class);

  @Test
  void getterIdReservaEsCorrecto(){
    assertEquals(123, servicio.getIdReserva());
  }

  @Test
  void getterProveedorEsCorrecto(){
    assertEquals("ProveedorA", servicio.proveedor());
  }

  @Test
  void getterCategoriaEsCorrecto(){
    assertEquals(Categoria.ALOJAMIENTO, servicio.categoria());
  }

  @Test
  void rechazaProveedorNulo() {
    assertThrows(CompletarCampos.class, () ->
            new ServicioIndividual(
                    "Hotel",
                    "Descripción",
                    new Cupo(2, 2),
                    123,
                    null,
                    150.0,
                    cat,
                    mock(Periodo.class)
            )
    );
  }

  @Test
  void rechazaProveedorVacio() {
    assertThrows(CompletarCampos.class, () ->
            new ServicioIndividual(
            "Hotel",
            "Descripción",
            new Cupo(2),
            123,
            "",
            150.0,
            cat
    ));

    assertThrows(CompletarCampos.class, () ->
            new ServicioIndividual(
            "Hotel",
            "Descripción",
            new Cupo(2),
            123,
            "   ",
            150.0,
            cat));
  }

  @Test
  void rechazaCategoriaNula() {
    assertThrows(CompletarCampos.class, () ->
            new ServicioIndividual(
                    "Hotel",
                    "Descripción",
                    new Cupo(2, 2),
                    123,
                    "Proveedor A",
                    150.0,
                    null,
                    mock(Periodo.class)
            )
    );
  }

  @Test
  void rechazaAtributoExtraNulo() {
    assertThrows(CompletarCampos.class,
            () -> servicio.agregarAtributoExtra(null));
  }

  @Test
  void agregaAtributoExtra() {
    servicio.agregarAtributoExtra(attDinamico);

    assertEquals(List.of(attDinamico), servicio.getAtributosDinamicos());
  }

  @Test
  void listaDeAtributosNoSePuedeModificar() {
    assertThrows(UnsupportedOperationException.class,
            () -> servicio.getAtributosDinamicos().add(attDinamico));
  }

  @Test
  void listaDevueltaNoEsLaListaInterna() {
    List<DefinicionAtributo> lista = servicio.getAtributosDinamicos();
    servicio.agregarAtributoExtra(attDinamico);

    assertTrue(lista.isEmpty());
    assertEquals(List.of(attDinamico), servicio.getAtributosDinamicos());
  }
}
