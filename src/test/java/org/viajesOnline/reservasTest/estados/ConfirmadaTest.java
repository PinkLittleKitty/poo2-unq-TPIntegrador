package org.viajesOnline.reservasTest.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.*;
import org.viajesOnline.reservas.estados.*;

import static org.mockito.Mockito.*;

public class ConfirmadaTest {
    private Confirmada estado;
    private Reserva contextoMock;
    private DetalleReserva detalleReservaMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        estado = new Confirmada();
        contextoMock = mock(Reserva.class);
        detalleReservaMock = mock(DetalleReserva.class);
        gestor = mock(GestorDeDocumentos.class);
    }

    @Test
    void siguiente() {
        estado.siguiente(contextoMock);

        verify(contextoMock).setEstadoActual(any(EnCheckin.class));
    }

    @Test
    void cancelar() {
        estado.cancelar(contextoMock);

        verify(contextoMock).setEstadoActual(any(Cancelada.class));
        verify(contextoMock).decrementarCupo();
    }

    @Test
    void notificar() {
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);

        estado.notificar(gestor, contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaConfirmada(contextoMock);
    }
}
