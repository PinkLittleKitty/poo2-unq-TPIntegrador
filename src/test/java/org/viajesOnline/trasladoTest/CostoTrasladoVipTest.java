package org.viajesOnline.trasladoTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.traslados.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CostoTrasladoVipTest {

    private ContextoTraslado contextoTraslado;
    private ContextoReserva reserva;
    private Traslado adaptadorTrasladoVip;
    private TrasladoVip trasladoVip;

    private PlataformaTransporte plataforma;
    private DireccionMock direccionDestino;
    private ContextoReserva reservaMock;

    @BeforeEach
    void setUp() {

        plataforma = mock(PlataformaTransporte.class);
        direccionDestino = mock(DireccionMock.class);
        reservaMock = mock(ContextoReserva.class);

        reserva = new ContextoReserva();
        trasladoVip = new TrasladoVip(100f,20f);
        adaptadorTrasladoVip = new AdaptadorTrasladoVip(trasladoVip);

        contextoTraslado = new ContextoTraslado(adaptadorTrasladoVip,4,reservaMock);
    }

    @Test
    void prueba() {


    }


    @Test
    void devuelveLaEstimacionDeLaPlataforma() {

        when(plataforma.estimarTraslado(4, direccionDestino)).thenReturn(500f);
        assertEquals(500f, plataforma.estimarTraslado(4, direccionDestino), 0.001f);
    }

    @Test
    void lePideLaEstimacionConLosDatosCorrectos() {

        plataforma.estimarTraslado(4, direccionDestino);
        verify(plataforma).estimarTraslado(4, direccionDestino);
    }
}


