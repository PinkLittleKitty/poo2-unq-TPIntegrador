package org.viajesOnline.reservasTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito.*;
import org.viajesOnline.items.Item;
import org.viajesOnline.reservas.DetalleReserva;
import org.viajesOnline.utils.Periodo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DetalleReservaTest {

    private DetalleReserva detalleReserva;
    private Item servicioA;
    private Item servicioB;
    private Periodo periodoMock;

    @BeforeEach
    public void setUp() {
        detalleReserva = new DetalleReserva();
        servicioA = mock(Item.class);
        servicioB = mock(Item.class);
        periodoMock = mock(Periodo.class);


        detalleReserva.agregarServicio(servicioA);
        detalleReserva.agregarServicio(servicioB);
    }

    @Test
    public void decrementarCupo() {
        detalleReserva.decrementarCupo(periodoMock);

        verify(servicioA, times(1)).decrementarCupo(periodoMock);
        verify(servicioB, times(1)).decrementarCupo(periodoMock);
    }

    @Test
    public void incrementarCupo() {
        detalleReserva.incrementarCupo(periodoMock);

        verify(servicioA, times(1)).incrementarCupo(periodoMock);
        verify(servicioB, times(1)).incrementarCupo(periodoMock);
    }

    @Test
    public void eliminarServicio() {
        detalleReserva.eliminarServicio(servicioA);

        assertFalse(detalleReserva.getServicios().contains(servicioA));
        assertTrue(detalleReserva.getServicios().contains(servicioB));
    }

    @Test
    void precioTotalServicios() {
        when(servicioA.precioFinal()).thenReturn(1500.0);
        when(servicioB.precioFinal()).thenReturn(2500.0);

        double total = detalleReserva.precioTotalServicios();

        assertEquals(4000.0, total, 0.001);
        verify(servicioA).precioFinal();
        verify(servicioB).precioFinal();
    }
}
