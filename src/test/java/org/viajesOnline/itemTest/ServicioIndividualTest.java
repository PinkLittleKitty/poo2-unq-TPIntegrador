package org.viajesOnline.itemTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.atributos.DefinicionAtributo;
import org.viajesOnline.items.Categoria;
import org.viajesOnline.items.ServicioIndividual;
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
  private Categoria catAloj = Categoria.ALOJAMIENTO;
  private ServicioIndividual servicio = new ServicioIndividual(
          "Hotel",
          "Descripción",
          10,
          123,
          "ProveedorA",
          150,
          catAloj,
          mock(Periodo.class)
  );
  private DefinicionAtributo attDinamico = mock(DefinicionAtributo.class);

  @Test
  void getterIdReservaEsCorrecto(){
    assertEquals(123, servicio.getIdReserva());
  }

  @Test
  void getterPrecioBaseEsCorrecto(){
    assertEquals(150.0, servicio.getPrecioBase());
  }

  @Test
  void rechazaProveedorNulo() {
    assertThrows(CompletarCampos.class, () ->
            new ServicioIndividual(
                    "Hotel",
                    "Descripción",
                    10,
                    123,
                    null,
                    150.0,
                    catAloj,
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
            10,
            123,
            "",
            150.0,
            catAloj,
                    mock(Periodo.class)
    ));

    assertThrows(CompletarCampos.class, () ->
            new ServicioIndividual(
            "Hotel",
            "Descripción",
            10,
            123,
            "   ",
            150.0,
            catAloj,
                    mock(Periodo.class)));
  }

  @Test
  void rechazaCategoriaNula() {
    assertThrows(CompletarCampos.class, () ->
            new ServicioIndividual(
                    "Hotel",
                    "Descripción",
                    10,
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
  void rechazaSiPrecioEsNegativoOCero() {
    assertThrows(CompletarCampos.class,
                        () -> new ServicioIndividual("Hotel", "Descripción", 10, 123,"ProvvedorA",0.0,  catAloj, mock(Periodo.class)));
    assertThrows(CompletarCampos.class,
                        () -> new ServicioIndividual("Hotel", "Descripción",  10, 123, "ProvvedorA",-1.0, catAloj, mock(Periodo.class)));
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
