package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.*;

import static org.mockito.Mockito.*;

public class EnCheckinTest {
    private EnCheckin estado;
    private ContextoReserva contextoMock;
    private Solicitud solicitudMock;

    @BeforeEach
    void setUp() {
        estado = new EnCheckin();
        contextoMock = mock(ContextoReserva.class);
        solicitudMock = mock(Solicitud.class);

        when(contextoMock.getSolicitud()).thenReturn(solicitudMock);
    }

    @Test
    void siguiente() {
        estado.siguiente(contextoMock);

        verify(contextoMock).setEstadoActual(any(EnCurso.class));
    }

    @Test
    void cancelar() {
        estado.cancelar(contextoMock);

        verify(solicitudMock).incrementarCupo();
        verify(contextoMock).reembolsar();
        verify(contextoMock).setEstadoActual(any(Cancelada.class));
    }

    @Test
    void notificar() {
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);

        estado.notificar(contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaEnCheckin(contextoMock);
    }
}
