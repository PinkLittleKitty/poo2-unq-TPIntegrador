package org.viajesOnline.trasladoTest;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.viajesOnline.traslados.Traslado;
import org.viajesOnline.traslados.TrasladoStandard;

import org.viajesOnline.traslados.DireccionMock;
import org.viajesOnline.traslados.PlataformaTransporte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CostoTrasladoTest {

    private PlataformaTransporte plataforma;
    private Traslado trasladoStandard;
    private DireccionMock direccionDestino;

    @BeforeEach
    void setUp() {

        plataforma = mock(PlataformaTransporte.class);
        direccionDestino = mock(DireccionMock.class);
        trasladoStandard = new TrasladoStandard(plataforma,direccionDestino);

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