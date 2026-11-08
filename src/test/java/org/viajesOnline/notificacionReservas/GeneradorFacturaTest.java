package org.viajesOnline.notificacionReservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.notificacionReservas.vouchers.ComprobanteFiscal;
import org.viajesOnline.reservas.ContextoReserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GeneradorFacturaTest {

    private GeneradorFactura generadorFactura;
    private ContextoReserva reservaMock;

    @BeforeEach
    void setUp() {
        generadorFactura = new GeneradorFactura();
        reservaMock = mock(ContextoReserva.class);
    }

    @Test
    void reservaFinalizada() {
        generadorFactura.reservaFinalizada(reservaMock);

        verify(reservaMock, times(1)).setVoucher(any(ComprobanteFiscal.class));
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
