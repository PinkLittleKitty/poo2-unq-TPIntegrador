package org.viajesOnline.reservasTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.items.Item;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.reservas.estados.EstadoReserva;
import org.viajesOnline.utils.Periodo;
import org.viajesOnline.utils.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaTest {

    private Reserva nuevaReserva;
    private EstadoReserva estadoMock;
    private EstadoReserva estadoNuevoMock;
    private ObservadorCambioEstado observerMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        estadoMock = mock(EstadoReserva.class);
        estadoNuevoMock = mock(EstadoReserva.class);
        observerMock = mock(ObservadorCambioEstado.class);
        gestor = mock(GestorDeDocumentos.class);
        nuevaReserva = new Reserva(gestor, mock(Usuario.class), mock(Periodo.class));
        nuevaReserva.setEstadoActual(estadoMock);
    }

    @Test
    void siguiente() {
        nuevaReserva.siguiente();

        verify(estadoMock, times(1)).siguiente(nuevaReserva);
    }

    @Test
    void cancelar() {
        nuevaReserva.cancelar();

        verify(estadoMock, times(1)).cancelar(nuevaReserva);
    }

    @Test
    void agregarServicio() {
        Item servicioMock = mock(Item.class);

        nuevaReserva.agregarServicio(servicioMock);

        verify(estadoMock, times(1)).agregarServicio(servicioMock, nuevaReserva.getDetalle());
    }

    @Test
    void setEstado() {
        nuevaReserva.agregarObservador(observerMock);

        nuevaReserva.setEstadoActual(estadoNuevoMock);

        assertEquals(estadoNuevoMock, nuevaReserva.getEstadoActual());
        verify(estadoNuevoMock, times(1)).notificar(gestor, nuevaReserva, observerMock);
    }

    @Test
    void quitarNotificador() {
        EstadoReserva estadoNuevo = mock(EstadoReserva.class);
        nuevaReserva.agregarObservador(observerMock);
        nuevaReserva.quitarObservador(observerMock);

        nuevaReserva.setEstadoActual(estadoNuevo);

        verify(estadoNuevo, never()).notificar(gestor, nuevaReserva, observerMock);
    }

    @Test
    void getUsuario() {
        assertNotNull(nuevaReserva.getUsuario());
    }

    @Test
    void reembolsar() {
        nuevaReserva.reembolsar();
    }

    @Test
    void precioFinalReserva() {
        Item servicioMock = mock(Item.class);
        when(servicioMock.precioFinal()).thenReturn(100.0);

        nuevaReserva.getDetalle().agregarServicio(servicioMock);

        assertEquals(100.0f, nuevaReserva.precioFinalReserva());
    }

    @Test
    void getPeriodo() {
        assertNotNull(contexto.getPeriodo());
    }

    @Test
    void getCostoTasas() {
        assertEquals(100.0, contexto.getCostoTasas());
    }

    @Test
    void decrementarEIncrementarCupo() {
        contexto.decrementarCupo();
        contexto.incrementarCupo();
    }
}
