package org.viajesOnline.reservasTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.Cupo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CupoTest {
  @Test
  void decrementarCupoDisminuyeElDisponible() {
    Cupo cupo = new Cupo(10, 7);

    cupo.decrementarCupo();

    assertEquals(6, cupo.getCupoDisponible());
    assertEquals(4, cupo.getCantCupoReservados());
  }

  @Test
  void incrementarCupoAumentaElDisponible() {
    Cupo cupo = new Cupo(10, 7);

    cupo.incrementarCupo();

    assertEquals(8, cupo.getCupoDisponible());
    assertEquals(2, cupo.getCantCupoReservados());
  }

  @Test
  void cupoReservadoEsLaDiferenciaEntreInicialYDisponible() {
    Cupo cupo = new Cupo(10, 7);

    assertEquals(3, cupo.getCantCupoReservados());
  }
}