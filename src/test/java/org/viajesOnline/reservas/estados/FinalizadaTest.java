package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.ContextoReserva;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class FinalizadaTest {
    @Test
    void finalizada_siguiente() {
        Finalizada estado = new Finalizada();
        ContextoReserva contextoMock = mock(ContextoReserva.class);

        assertThrows(UnsupportedOperationException.class, () -> estado.siguiente(contextoMock));
    }
}
