package org.viajesOnline;

import org.junit.jupiter.api.Test;
import org.viajesOnline.atributos.DefinicionAtributo;
import org.viajesOnline.atributos.Numerico;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServicioIndividualTest {
  private Categoria cat = Categoria.ALOJAMIENTO;
  private ServicioIndividual servicio = new ServicioIndividual(
          "Hotel",
          "Descripción",
          2,
          123,
          "ProveedorA",
          150.0,
          cat
  );
  private DefinicionAtributo attDinamico = new DefinicionAtributo("RatingEstrellas", true, 5, new Numerico());

  @Test
  void creaServicioConDatosValidos() {
    assertNotNull(servicio);
    assertEquals("ProveedorA", servicio.proveedor());
    assertNotNull(servicio.categoria());
    assertNotNull(servicio.getAtributosDinamicos());
  }

  @Test
  void rechazaNombreNulo() {
    assertThrows(IllegalArgumentException.class, () ->
            new ServicioIndividual(
                    null,
                    "Descripción",
                    2,
                    123,
                    "ProveedorA",
                    150.0,
                    cat
            )
    );
  }

  @Test
  void rechazaDescripcionNula() {
    assertThrows(IllegalArgumentException.class, () ->
            new ServicioIndividual(
                    "Hotel",
                    null,
                    2,
                    123,
                    "ProveedorA",
                    150.0,
                    cat
            )
    );
  }

  @Test
  void rechazaProveedorNulo() {
    assertThrows(IllegalArgumentException.class, () ->
            new ServicioIndividual(
                    "Hotel",
                    "Descripción",
                    2,
                    123,
                    null,
                    150.0,
                    cat
            )
    );
  }

  @Test
  void rechazaCategoriaNula() {
    assertThrows(IllegalArgumentException.class, () ->
            new ServicioIndividual(
                    "Hotel",
                    "Descripción",
                    2,
                    123,
                    "Proveedor A",
                    150.0,
                    null
            )
    );
  }

  @Test
  void rechazaAtributoExtraNulo() {
    assertThrows(IllegalArgumentException.class,
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
