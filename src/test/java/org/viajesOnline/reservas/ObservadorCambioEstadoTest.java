package org.viajesOnline.reservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.GestorDeDocumentos;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

class ObservadorCambioEstadoTest {

    private ObservadorCambioEstado observadorPorDefecto;
    private ContextoReserva contextoMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        observadorPorDefecto = new ObservadorCambioEstado() {};
        contextoMock = mock(ContextoReserva.class);
        gestor = mock(GestorDeDocumentos.class);
    }

    @Test
    void default_reservaPendiente() {
        assertDoesNotThrow(() -> observadorPorDefecto.reservaPendiente(contextoMock));
        verifyNoInteractions(contextoMock);
    }

    @Test
    void default_reservaConfirmada() {
        assertDoesNotThrow(() -> observadorPorDefecto.reservaConfirmada(contextoMock));
        verifyNoInteractions(contextoMock);
    }

    @Test
    void default_reservaEnCheckin() {
        assertDoesNotThrow(() -> observadorPorDefecto.reservaEnCheckin(contextoMock));
        verifyNoInteractions(contextoMock);
    }

    @Test
    void default_reservaEnCurso() {
        assertDoesNotThrow(() -> observadorPorDefecto.reservaEnCurso(contextoMock));
        verifyNoInteractions(contextoMock);
    }

    @Test
    void default_reservaFinalizada() {
        assertDoesNotThrow(() -> observadorPorDefecto.reservaFinalizada(gestor, contextoMock));
        verifyNoInteractions(contextoMock);
    }

    @Test
    void default_reservaCancelada() {
        assertDoesNotThrow(() -> observadorPorDefecto.reservaCancelada(contextoMock));
        verifyNoInteractions(contextoMock);
    }
}

