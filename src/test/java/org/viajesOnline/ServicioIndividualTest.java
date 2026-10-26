package org.viajesOnline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServicioIndividualTest {
  private Categoria cat = Categoria.ALOJAMIENTO;

  @Test
  void creaServicioConDatosValidos() {
    ServicioIndividual servicio = new ServicioIndividual(
            "Hotel",
            "Descripción",
            2,
            123,
            "ProveedorA",
            150.0,
            cat
    );

    assertNotNull(servicio);
    assertEquals("ProveedorA", servicio.proovedor());
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
}
