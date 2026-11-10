package org.viajesOnline.trasladoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.traslados.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContextoTrasladoVipTest {

    private ContextoTraslado contextoTraslado;
    //private AdaptadorTrasladoVip adaptadorTrasladoVip;


    private TrasladoVip trasladoVipMock;
    private TrasladoVip otroTrasladoVipMock;
    private ContextoReserva reservaMock;
    private ContextoReserva nuevaReservaMock;
    private AdaptadorTrasladoVip adaptadorTrasladoVip;;

    @BeforeEach
    void setUp() {

        reservaMock = mock(ContextoReserva.class);
        trasladoVipMock = mock(TrasladoVip.class);
        otroTrasladoVipMock = mock(TrasladoVip.class);
        adaptadorTrasladoVip = new AdaptadorTrasladoVip(trasladoVipMock);

        contextoTraslado = new ContextoTraslado(adaptadorTrasladoVip,4,reservaMock);
    }

    @Test
    void costoVipConReservaDe1000Es900() {
        when(reservaMock.precioFinalReserva()).thenReturn(1000f);

        when(trasladoVipMock.calcularCosto(1000f)).thenReturn(900f);


        assertEquals(900f, contextoTraslado.costoParaTipoDeTraslado(), 0.001f);

        verify(reservaMock).precioFinalReserva();
        verify(trasladoVipMock).calcularCosto(1000f);
    }

    @Test
    void CambioEltrasladoAlADAPTADOREnElMismoContextoTrasladoTieneUnaReservaConPrecioDe1000PCalculaYda900() {

        when(reservaMock.precioFinalReserva()).thenReturn(1000f);
        when(trasladoVipMock.calcularCosto(1000.0f)).thenReturn(900f);

        float costo = (float) contextoTraslado.costoParaTipoDeTraslado();
        assertEquals(900f, costo,0.001f);
        verify(reservaMock).precioFinalReserva();
        verify(trasladoVipMock).calcularCosto(1000f);
    }

}


