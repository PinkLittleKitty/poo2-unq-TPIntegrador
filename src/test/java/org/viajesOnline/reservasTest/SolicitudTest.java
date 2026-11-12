package org.viajesOnline.reservasTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.Catalogo;
import org.viajesOnline.reservas.Solicitud;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SolicitudTest {

    private Solicitud solicitud;
    private Catalogo servicioA;
    private Catalogo servicioB;

    @BeforeEach
    public void setUp() {
        solicitud = new Solicitud();
        servicioA = mock(Catalogo.class);
        servicioB = mock(Catalogo.class);

        solicitud.agregarServicio(servicioA);
        solicitud.agregarServicio(servicioB);
    }

    @Test
    public void decrementarCupo() {
        solicitud.decrementarCupo();

        verify(servicioA, times(1)).decrementarCupo();
        verify(servicioB, times(1)).decrementarCupo();
    }

    @Test
    public void incrementarCupo() {
        solicitud.incrementarCupo();

        verify(servicioA, times(1)).incrementarCupo();
        verify(servicioB, times(1)).incrementarCupo();
    }

    @Test
    public void eliminarServicio() {
        solicitud.eliminarServicio(servicioA);

        assertFalse(solicitud.getServicios().contains(servicioA));
        assertTrue(solicitud.getServicios().contains(servicioB));
    }

    @Test
    void precioTotalServicios() {
        when(servicioA.precioFinal()).thenReturn(1500.0);
        when(servicioB.precioFinal()).thenReturn(2500.0);

        double total = solicitud.precioTotalServicios();

        assertEquals(4000.0, total, 0.001);
        verify(servicioA).precioFinal();
        verify(servicioB).precioFinal();
    }
}
