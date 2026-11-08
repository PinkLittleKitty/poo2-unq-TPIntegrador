package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.reservas.Solicitud;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EstadoReservaTest {

    private Catalogo servicioMock;
    private Solicitud solicitudMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        servicioMock = mock(Catalogo.class);
        solicitudMock = mock(Solicitud.class);
        gestor = mock(GestorDeDocumentos.class);
    }

    @Test
    void estadoPorDefecto_agregarServicio() {
        EstadoReserva estadoPorDefecto = new EstadoReserva() {};

        assertThrows(UnsupportedOperationException.class, () -> {
            estadoPorDefecto.agregarServicio(servicioMock, solicitudMock);
        });

        verifyNoInteractions(solicitudMock);
        verifyNoInteractions(servicioMock);
    }

    @Test
    void confirmada_agregarServicio() {
        EstadoReserva estadoConfirmada = new Confirmada();

        assertThrows(UnsupportedOperationException.class, () -> {
            estadoConfirmada.agregarServicio(servicioMock, solicitudMock);
        });

        verifyNoInteractions(solicitudMock);
    }

    @Test
    void solicitudPendiente_agregarServicio() {
        EstadoReserva solicitudPendiente = new SolicitudPendiente();

        solicitudPendiente.agregarServicio(servicioMock, solicitudMock);

        verify(solicitudMock, times(1)).agregarServicio(servicioMock);
    }

    @Test
    void default_siguiente() {
        EstadoReserva estado = new EstadoReserva() {};
        assertThrows(UnsupportedOperationException.class, () -> estado.siguiente(mock(ContextoReserva.class)));
    }

    @Test
    void default_decrementarCupo() {
        EstadoReserva estado = new EstadoReserva() {};
        assertThrows(UnsupportedOperationException.class, () -> estado.decrementarCupo(mock(Solicitud.class)));
    }

    @Test
    void default_cancelar() {
        EstadoReserva estado = new EstadoReserva() {};
        ContextoReserva contextoMock = mock(ContextoReserva.class);
        estado.cancelar(contextoMock);

        verify(contextoMock).setEstadoActual(any(Cancelada.class));
    }

    @Test
    void default_notificar() {
        EstadoReserva estado = new EstadoReserva() {};
        ContextoReserva contextoMock = mock(ContextoReserva.class);
        ObservadorCambioEstado observerMock = mock(ObservadorCambioEstado.class);

        assertDoesNotThrow(() -> estado.notificar(gestor, contextoMock, observerMock));
        verifyNoInteractions(observerMock);
    }
}

