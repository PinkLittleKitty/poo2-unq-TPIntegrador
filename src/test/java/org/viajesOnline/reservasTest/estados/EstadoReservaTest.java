package org.viajesOnline.reservasTest.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.items.Item;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.reservas.DetalleReserva;
import org.viajesOnline.reservas.estados.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstadoReservaTest {

    private Item servicioMock;
    private DetalleReserva detalleReservaMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        servicioMock = mock(Item.class);
        detalleReservaMock = mock(DetalleReserva.class);
        gestor = mock(GestorDeDocumentos.class);
    }

    @Test
    void estadoPorDefecto_agregarServicio() {
        EstadoReserva estadoPorDefecto = new EstadoReserva() {};

        assertThrows(UnsupportedOperationException.class, () -> {
            estadoPorDefecto.agregarServicio(servicioMock, detalleReservaMock);
        });

        verifyNoInteractions(detalleReservaMock);
        verifyNoInteractions(servicioMock);
    }

    @Test
    void confirmada_agregarServicio() {
        EstadoReserva estadoConfirmada = new Confirmada();

        assertThrows(UnsupportedOperationException.class, () -> {
            estadoConfirmada.agregarServicio(servicioMock, detalleReservaMock);
        });

        verifyNoInteractions(detalleReservaMock);
    }

    @Test
    void solicitudPendiente_agregarServicio() {
        EstadoReserva solicitudPendiente = new SolicitudPendiente();

        solicitudPendiente.agregarServicio(servicioMock, detalleReservaMock);

        verify(detalleReservaMock, times(1)).agregarServicio(servicioMock);
    }

    @Test
    void default_siguiente() {
        EstadoReserva estado = new EstadoReserva() {};
        assertThrows(UnsupportedOperationException.class, () -> estado.siguiente(mock(Reserva.class)));
    }

    @Test
    void default_cancelar() {
        EstadoReserva estado = new EstadoReserva() {};
        Reserva contextoMock = mock(Reserva.class);
        estado.cancelar(contextoMock);

        verify(contextoMock).setEstadoActual(any(Cancelada.class));
    }

    @Test
    void default_notificar() {
        EstadoReserva estado = new EstadoReserva() {};
        Reserva contextoMock = mock(Reserva.class);
        ObservadorCambioEstado observerMock = mock(ObservadorCambioEstado.class);

        assertDoesNotThrow(() -> estado.notificar(gestor, contextoMock, observerMock));
        verifyNoInteractions(observerMock);
    }

    @Test
    void default_tasasReembolsables() {
        EstadoReserva estado = new EstadoReserva() {};
        Reserva r = mock(Reserva.class);
        assertEquals(0.0, estado.tasasReembolsables(r));
    }
}

