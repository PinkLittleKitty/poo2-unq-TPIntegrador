package org.viajesOnline.trasladoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.traslados.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CostoTrasladoVipTest {

    private ContextoTraslado contextoTraslado;
    //private AdaptadorTrasladoVip adaptadorTrasladoVip;


    private TrasladoVip trasladoVipMock;
    private TrasladoVip otroTrasladoVipMock;
    private ContextoReserva reservaMock;
    private AdaptadorTrasladoVip adaptadorTrasladoVipMock;;

    @BeforeEach
    void setUp() {

        reservaMock = mock(ContextoReserva.class);
        trasladoVipMock = mock(TrasladoVip.class);
        otroTrasladoVipMock = mock(TrasladoVip.class);
        adaptadorTrasladoVipMock = mock(AdaptadorTrasladoVip.class);



        contextoTraslado = new ContextoTraslado(adaptadorTrasladoVipMock,4,reservaMock);
    }

    @Test
    void costoVipConReservaDe1000Es900() {
        when(reservaMock.precioFinalReserva()).thenReturn(1000f);
        doNothing().when(adaptadorTrasladoVipMock).setTrasladoVip(trasladoVipMock);

        //when(trasladoVipMock.calcularCosto(reservaMock.precioFinalReserva())).thenReturn(900f);


        assertEquals(900f, contextoTraslado.costoParaTipoDeTraslado(), 0.001f);

        verify(reservaMock).precioFinalReserva();
    }

    @Test
    void ContextoTrasladoTieneUnaReservaConPrecioDe1000PesosCalculoELcOSTOYd900() {

        when(reservaMock.precioFinalReserva()).thenReturn(1000f);

        //when(adaptadorTrasladoVipMock.calcularCosto(reservaMock.precioFinalReserva()).thenReturn(900f);

        when(trasladoVipMock.calcularCosto(reservaMock.precioFinalReserva())).thenReturn(900f);

       // when(contextoTraslado.calcularCosto(reservaMock.precioFinalReserva())).thenReturn(900f);

        //se verifica que se llamo a precio reserva

        verify(reservaMock).precioFinalReserva();
        verify(trasladoVipMock).calcularCosto(1000f);
    }

    @Test
    void CambioEltrasladoAlADAPTADOREnElMismoContextoTrasladoTieneUnaReservaConPrecioDe1000PCalculaYda900() {

        when(reservaMock.precioFinalReserva()).thenReturn(1000f);

        //adaptadorTrasladoVip.setTrasladoVip(otroTrasladoVipMock);

        assertEquals(800f, contextoTraslado.costoParaTipoDeTraslado(),0.001f);


        //assertEquals(300f, adaptadorTrasladoVip.trasladoVip().cargoBase(),0.001f);
        //when(trasladoVipMock.cargoBase()).thenReturn(100f);
        //when(trasladoVipMock.porcentajeFijo()).thenReturn(0.8f);

    }

}


