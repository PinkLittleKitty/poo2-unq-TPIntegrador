package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.*;

import static org.mockito.Mockito.*;

public class EnCursoTest {
    @Test
    void siguiente() {
        EnCurso estado = new EnCurso();
        ContextoReserva contextoMock = mock(ContextoReserva.class);

        estado.siguiente(contextoMock);

        verify(contextoMock).setEstadoActual(any(Finalizada.class));
    }
}
