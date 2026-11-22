package org.viajesOnline.reservasTest.estados;

import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.*;
import org.viajesOnline.reservas.estados.*;

import static org.mockito.Mockito.*;

public class EnCursoTest {
    @Test
    void siguiente() {
        EnCurso estado = new EnCurso();
        Reserva contextoMock = mock(Reserva.class);

        estado.siguiente(contextoMock);

        verify(contextoMock).setEstadoActual(any(Finalizada.class));
    }

    @Test
    void notificar() {
        EnCurso estado = new EnCurso();
        Reserva contextoMock = mock(Reserva.class);
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);
        GestorDeDocumentos gestor = mock(GestorDeDocumentos.class);

        estado.notificar(gestor, contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaEnCurso(contextoMock);
    }

    @Test
    void cancelar() {
        EnCurso estado = new EnCurso();
        Reserva contextoMock = mock(Reserva.class);

        estado.cancelar(contextoMock);

        verify(contextoMock).reembolsar();
        verify(contextoMock).setEstadoActual(any(Cancelada.class));
    }
}
