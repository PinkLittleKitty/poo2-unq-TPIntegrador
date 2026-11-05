package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.reservas.*;

import static org.mockito.Mockito.*;

public class SolicitudPendienteTest {
    private SolicitudPendiente estado;
    private ContextoReserva contextoMock;
    private Solicitud solicitudMock;

    @BeforeEach
    void setUp() {
        estado = new SolicitudPendiente();
        contextoMock = mock(ContextoReserva.class);
        solicitudMock  = mock(Solicitud.class);

        when (contextoMock.getSolicitud()).thenReturn(solicitudMock);
    }

    @Test
    void siguiente() {
        estado.siguiente(contextoMock);

        verify(contextoMock).setEstadoActual(any(Confirmada.class));

        verify(solicitudMock).decrementarCupo();
    }

    @Test
    void agregarServicio() {
        Catalogo servicioMock = mock(Catalogo.class);

        estado.agregarServicio(servicioMock, solicitudMock);

        verify(solicitudMock).agregarServicio(servicioMock);
    }

    @Test
    void cancelar() {
        estado.cancelar(contextoMock);

        verify(contextoMock).setEstadoActual(any(Cancelada.class));
    }
}
