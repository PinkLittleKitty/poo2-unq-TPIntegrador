package org.viajesOnline.reservasTest.estados;

import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.reservas.estados.Finalizada;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FinalizadaTest {
    @Test
    void siguiente() {
        Finalizada estado = new Finalizada();
        ContextoReserva contextoMock = mock(ContextoReserva.class);

        assertThrows(UnsupportedOperationException.class, () -> estado.siguiente(contextoMock));
    }

    @Test
    void notificar() {
        Finalizada estado = new Finalizada();
        ContextoReserva contextoMock = mock(ContextoReserva.class);
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);
        GestorDeDocumentos gestor = mock(GestorDeDocumentos.class);

        estado.notificar(gestor, contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaFinalizada(gestor, contextoMock);
    }
}
