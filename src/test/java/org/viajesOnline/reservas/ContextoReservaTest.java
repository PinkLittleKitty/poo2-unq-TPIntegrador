package org.viajesOnline.reservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.notificacionReservas.vouchers.Voucher;
import org.viajesOnline.reservas.estados.EstadoReserva;
import org.viajesOnline.utils.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContextoReservaTest {

    private ContextoReserva contexto;
    private EstadoReserva estadoMock;
    private EstadoReserva estadoNuevoMock;
    private ObservadorCambioEstado observerMock;

    @BeforeEach
    void setUp() {
        contexto = new ContextoReserva(new Usuario());
        estadoMock = mock(EstadoReserva.class);
        estadoNuevoMock = mock(EstadoReserva.class);
        contexto.setEstadoActual(estadoMock);
        observerMock = mock(ObservadorCambioEstado.class);
    }

    @Test
    void siguiente() {
        contexto.siguiente();

        verify(estadoMock, times(1)).siguiente(contexto);
    }

    @Test
    void cancelar() {
        contexto.cancelar();

        verify(estadoMock, times(1)).cancelar(contexto);
    }

    @Test
    void agregarServicio() {
        Catalogo servicioMock = mock(Catalogo.class);

        contexto.agregarServicio(servicioMock);

        verify(estadoMock, times(1)).agregarServicio(servicioMock, contexto.getSolicitud());
    }

    @Test
    void setEstado() {;
        contexto.agregarObservador(observerMock);

        contexto.setEstadoActual(estadoNuevoMock);

        assertEquals(estadoNuevoMock, contexto.getEstadoActual());
        verify(observerMock, times(1)).cambioDeEstado(contexto, estadoMock, estadoNuevoMock);
    }

    @Test
    void quitarNotificador() {
        contexto.agregarObservador(observerMock);
        contexto.quitarObservador(observerMock);

        contexto.setEstadoActual(mock(EstadoReserva.class));

        verifyNoInteractions(observerMock);
    }

    @Test
    void getUsuario() {
        assertNotNull(contexto.getUsuario());
    }

    @Test
    void voucher_getterYsetter() {
        Voucher voucherMock = mock(Voucher.class);
        contexto.setVoucher(voucherMock);
        assertEquals(voucherMock, contexto.getVoucher());
    }

    @Test
    void reembolsar() {
        contexto.reembolsar();
    }

    @Test
    void precioFinalReserva() {
        Catalogo servicioMock =mock(Catalogo.class);
        when(servicioMock.precioFinal()).thenReturn(100.0);

        contexto.getSolicitud().agregarServicio(servicioMock);

        assertEquals(100.0f, contexto.precioFinalReserva());
    }
}

