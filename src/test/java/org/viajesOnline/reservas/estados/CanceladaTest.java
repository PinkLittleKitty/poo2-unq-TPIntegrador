package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CanceladaTest {
    @Test
    void cancelada_siguiente() {
        Cancelada estado = new Cancelada();
        ContextoReserva contextoMock = mock(ContextoReserva.class);

        assertThrows(UnsupportedOperationException.class, () -> estado.siguiente(contextoMock));
    }
}
