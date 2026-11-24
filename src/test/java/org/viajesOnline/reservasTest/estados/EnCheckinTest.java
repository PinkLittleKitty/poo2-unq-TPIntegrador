package org.viajesOnline.reservasTest.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.*;
import org.viajesOnline.reservas.estados.*;

import static org.mockito.Mockito.*;

public class EnCheckinTest {
    private EnCheckin estado;
    private Reserva contextoMock;
    private DetalleReserva detalleReservaMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        estado = new EnCheckin();
        contextoMock = mock(Reserva.class);
        detalleReservaMock = mock(DetalleReserva.class);
        gestor = mock(GestorDeDocumentos.class);

        when(contextoMock.getDetalle()).thenReturn(detalleReservaMock);
    }

    @Test
    void siguiente() {
        estado.siguiente(contextoMock);

        verify(contextoMock).setEstadoActual(any(EnCurso.class));
    }

    @Test
    void cancelar() {
        estado.cancelar(contextoMock);

        verify(contextoMock).incrementarCupo();
        verify(contextoMock).reembolsar();
        verify(contextoMock).setEstadoActual(any(Cancelada.class));
    }

    @Test
    void notificar() {
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);

        estado.notificar(gestor, contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaEnCheckin(contextoMock);
    }
}
