package org.viajesOnline.notificacionReservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.ComprobanteFiscal;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GeneradorFacturaTest {

    private GeneradorFactura generadorFactura;
    private ContextoReserva reservaMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        generadorFactura = new GeneradorFactura();
        reservaMock = mock(ContextoReserva.class);
        gestor = mock(GestorDeDocumentos.class);
    }

    @Test
    void reservaFinalizada() {
        generadorFactura.reservaFinalizada(gestor, reservaMock);

        verify(gestor, times(1)).registrarDocumento(any(ComprobanteFiscal.class));
    }

    @Test
    void eventosNoSuscritos() {
        generadorFactura.reservaPendiente(reservaMock);
        generadorFactura.reservaConfirmada(reservaMock);
        generadorFactura.reservaEnCheckin(reservaMock);
        generadorFactura.reservaEnCurso(reservaMock);
        generadorFactura.reservaCancelada(reservaMock);

        verifyNoInteractions(reservaMock);
    }
}
