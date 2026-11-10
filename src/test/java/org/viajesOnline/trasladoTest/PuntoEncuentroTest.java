package org.viajesOnline.trasladoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.traslados.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class PuntoEncuentroTest {

    private ContextoTraslado contextoTrasladoMock;
    private PuntoEncuentro puntoEncuentro;
    private LugarPuntoEncuentro terminalMock;
    private LugarPuntoEncuentro aeropueroMock;

    @BeforeEach
    void setUp() {

        terminalMock = mock(LugarPuntoEncuentro.class);
        aeropueroMock = mock(LugarPuntoEncuentro.class);
        contextoTrasladoMock = mock(ContextoTraslado.class);

        puntoEncuentro = new PuntoEncuentro(terminalMock,false);
    }

    @Test
    void testPUNTOeNCUENTOSeSETEAuNNUEVOpUNTOeNCUENTRO(){

        puntoEncuentro.setPuntoEncuentro(aeropueroMock);
        assertEquals(aeropueroMock,puntoEncuentro.puntoEncuentro() );
    }

    @Test
    void suPrecioPARAtRASLADOSEsCERO(){

        assertEquals(0,puntoEncuentro.precioEnPuntoEncuentro() );
    }

    @Test
    void excepcionPorNOhaberGuiasDisponibles(){

        assertThrows(UnsupportedOperationException.class,
                      () -> puntoEncuentro.hayGuiasDisponibles());
    }

    @Test
    void hayGuiasDisponibles(){

        puntoEncuentro.setGuiasDisponibles(true);
        assertTrue(puntoEncuentro.hayGuiasDisponibles() );
    }

    @Test
    void seCalculaELcosto() {

        when(contextoTrasladoMock.precioReserva()).thenReturn(1000f);

        float costo = puntoEncuentro.calcularCosto(contextoTrasladoMock);

        assertEquals(0f, costo, 0.001f);

    }


}


