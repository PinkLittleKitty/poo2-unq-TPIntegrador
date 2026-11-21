package org.viajesOnline.reservasTest.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.catalogo.Item;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.*;
import org.viajesOnline.reservas.estados.*;

import static org.mockito.Mockito.*;

public class DetalleReservaPendienteTest {
    private SolicitudPendiente estado;
    private Reserva contextoMock;
    private DetalleReserva detalleReservaMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        estado = new SolicitudPendiente();
        contextoMock = mock(Reserva.class);
        detalleReservaMock = mock(DetalleReserva.class);
        gestor = mock(GestorDeDocumentos.class);

        when (contextoMock.getSolicitud()).thenReturn(detalleReservaMock);
    }

    @Test
    void siguiente() {
        estado.siguiente(contextoMock);

        verify(contextoMock).setEstadoActual(any(Confirmada.class));

        verify(detalleReservaMock).decrementarCupo();
    }

    @Test
    void agregarServicio() {
        Item servicioMock = mock(Item.class);

        estado.agregarServicio(servicioMock, detalleReservaMock);

        verify(detalleReservaMock).agregarServicio(servicioMock);
    }

    @Test
    void cancelar() {
        estado.cancelar(contextoMock);

        verify(contextoMock).setEstadoActual(any(Cancelada.class));
    }

    @Test
    void notificar() {
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);

        estado.notificar(gestor, contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaPendiente(contextoMock);
    }
}
