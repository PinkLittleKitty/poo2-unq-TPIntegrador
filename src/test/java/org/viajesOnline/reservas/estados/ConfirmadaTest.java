package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.*;

import static org.mockito.Mockito.*;

public class ConfirmadaTest {
    private Confirmada estado;
    private ContextoReserva contextoMock;
    private Solicitud solicitudMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        estado = new Confirmada();
        contextoMock = mock(ContextoReserva.class);
        solicitudMock = mock(Solicitud.class);
        gestor = mock(GestorDeDocumentos.class);
    }

    @Test
    void siguiente() {
        estado.siguiente(contextoMock);

        verify(contextoMock).setEstadoActual(any(EnCheckin.class));
    }

    @Test
    void decrementarCupo() {
        estado.decrementarCupo(solicitudMock);

        verify(solicitudMock).decrementarCupo();
    }

    @Test
    void notificar() {
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);

        estado.notificar(gestor, contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaConfirmada(contextoMock);
    }
}
