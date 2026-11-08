package org.viajesOnline.notificacionReservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.ContextoReserva;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

class ClubViajerosTest {

    private ClubViajeros clubViajeros;
    private ContextoReserva reservaMock;

    @BeforeEach
    void setUp() {
        clubViajeros = new ClubViajeros();
        reservaMock = mock(ContextoReserva.class);
    }

    @Test
    void reservaCancelada() {
        assertDoesNotThrow(() -> clubViajeros.reservaCancelada(reservaMock));
    }

    @Test
    void eventosNoSuscritos() {
        assertDoesNotThrow(() -> {
            clubViajeros.reservaPendiente(reservaMock);
            clubViajeros.reservaConfirmada(reservaMock);
            clubViajeros.reservaEnCheckin(reservaMock);
            clubViajeros.reservaEnCurso(reservaMock);
            clubViajeros.reservaFinalizada(reservaMock);
        });
        verifyNoInteractions(reservaMock);
    }
}

