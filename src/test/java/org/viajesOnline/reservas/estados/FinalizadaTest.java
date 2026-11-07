package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FinalizadaTest {
    @Test
    void finalizada_siguiente() {
        Finalizada estado = new Finalizada();
        ContextoReserva contextoMock = mock(ContextoReserva.class);

        assertThrows(UnsupportedOperationException.class, () -> estado.siguiente(contextoMock));
    }

    @Test
    void notificar() {
        Finalizada estado = new Finalizada();
        ContextoReserva contextoMock = mock(ContextoReserva.class);
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);

        estado.notificar(contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaFinalizada(contextoMock);
    }
}
