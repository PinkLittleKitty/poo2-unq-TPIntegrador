package org.viajesOnline.trasladoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.traslados.AdaptadorTrasladoVip;
import org.viajesOnline.traslados.ContextoTraslado;
import org.viajesOnline.traslados.TrasladoVip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class AdaptadorTrasladoVipTest {

    private TrasladoVip otroTrasladoVipMock;
    private TrasladoVip trasladoVipMock;
    private AdaptadorTrasladoVip adaptadorTrasladoVip;
    private ContextoTraslado contextoTrasladoMock;

    @BeforeEach
    void setUp() {

        contextoTrasladoMock = mock(ContextoTraslado.class);
        trasladoVipMock = mock(TrasladoVip.class);
        otroTrasladoVipMock = mock(TrasladoVip.class);
        adaptadorTrasladoVip = new AdaptadorTrasladoVip(trasladoVipMock);
    }

    @Test
    void seCalculaELcosto() {

        when(contextoTrasladoMock.precioReserva()).thenReturn(1000f);
        when(trasladoVipMock.calcularCosto(contextoTrasladoMock.precioReserva())).thenReturn(900f);

        float costo = adaptadorTrasladoVip.calcularCosto(contextoTrasladoMock);

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


