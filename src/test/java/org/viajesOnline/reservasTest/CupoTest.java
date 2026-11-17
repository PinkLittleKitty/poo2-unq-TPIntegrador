package org.viajesOnline.reservasTest;

import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.Cupo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CupoTest {
  @Test
  void sumarSumaLosValoresDeAmbosObjetos() {
    Cupo primero = new Cupo(10, 7);
    Cupo segundo = new Cupo(5, 3);

    Cupo resultado = primero.sumar(segundo);

    assertEquals(15, resultado.getCupoInicial());
    assertEquals(10, resultado.getCupoDisponible());
    assertEquals(5, resultado.getCupoReservado());
  }

  @Test
  void decrementarCupoDisminuyeElDisponible() {
    Cupo cupo = new Cupo(10, 7);

    cupo.decrementarCupo();

    assertEquals(6, cupo.getCupoDisponible());
    assertEquals(4, cupo.getCupoReservado());
  }

  @Test
  void incrementarCupoAumentaElDisponible() {
    Cupo cupo = new Cupo(10, 7);

    cupo.incrementarCupo();

    assertEquals(8, cupo.getCupoDisponible());
    assertEquals(2, cupo.getCupoReservado());
  }

  @Test
  void cupoReservadoEsLaDiferenciaEntreInicialYDisponible() {
    Cupo cupo = new Cupo(10, 7);

    assertEquals(3, cupo.getCupoReservado());
  }
}