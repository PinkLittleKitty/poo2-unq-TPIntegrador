package org.viajesOnline.trasladoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.traslados.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class PuntoEncuentroTest {

    private TrasladoVip otroTrasladoVipMock;
    private TrasladoVip trasladoVipMock;
    private AdaptadorTrasladoVip adaptadorTrasladoVip;
    private ContextoTraslado contextoTrasladoMock;

    private PuntoEncuentro puntoEncuentro;
    private LugarPuntoEncuentro terminalMock;
    private LugarPuntoEncuentro aeropueroMock;

    @BeforeEach
    void setUp() {

        terminalMock = mock(LugarPuntoEncuentro.class);
        aeropueroMock = mock(LugarPuntoEncuentro.class);

        puntoEncuentro = new PuntoEncuentro(terminalMock,true);
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
    void suPrecioPARAtRASLADOSEsCERO(){

        assertEquals(0,puntoEncuentro.precioEnPuntoEncuentro() );
    }

    @Test
    void verificaSiHayGuiasDisponibles(){

        assertTrue(0,puntoEncuentro.precioEnPuntoEncuentro() );
    }

    @Test
    void seCalculaELcosto() {

        when(contextoTrasladoMock.precioReserva()).thenReturn(1000f);
        when(trasladoVipMock.calcularCosto(contextoTrasladoMock.precioReserva())).thenReturn(900f);

        float costo = puntoEncuentro.calcularCosto(contextoTrasladoMock);

        verify(contextoTrasladoMock, times(2)).precioReserva();

        assertEquals(900f, costo, 0.001f);

        verify(trasladoVipMock, atLeastOnce()).calcularCosto(1000f);

        assertEquals(900f, new TrasladoVip(100f, 20f).calcularCosto(1000f), 0.001f);

    }


    @Test
    void elAdaptadorTieneTrasladoVipYLuegoSeSEteaAotroTrasladoVipMock() {

        assertEquals(trasladoVipMock, adaptadorTrasladoVip.trasladoVip());

        adaptadorTrasladoVip.setTrasladoVip(otroTrasladoVipMock);

        assertEquals(otroTrasladoVipMock, adaptadorTrasladoVip.trasladoVip());
    }

}


