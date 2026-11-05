package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.*;

import static org.mockito.Mockito.*;

public class ConfirmadaTest {
    private Confirmada estado;
    private ContextoReserva contextoMock;
    private Solicitud solicitudMock;

    @BeforeEach
    void setUp() {
        estado = new Confirmada();
        contextoMock = mock(ContextoReserva.class);
        solicitudMock = mock(Solicitud.class);
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
}
