package org.viajesOnline.reservas.estados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.*;

import static org.mockito.Mockito.*;

public class SolicitudPendienteTest {
    private SolicitudPendiente estado;
    private ContextoReserva contextoMock;
    private Solicitud solicitudMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        estado = new SolicitudPendiente();
        contextoMock = mock(ContextoReserva.class);
        solicitudMock  = mock(Solicitud.class);
        gestor = mock(GestorDeDocumentos.class);

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

    @Test
    void notificar() {
        ObservadorCambioEstado observadorMock = mock(ObservadorCambioEstado.class);

        estado.notificar(gestor, contextoMock, observadorMock);

        verify(observadorMock, times(1)).reservaPendiente(contextoMock);
    }
}
