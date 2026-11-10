package org.viajesOnline.notificacionReservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.viajesOnline.documento.ComprobanteFiscal;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.documento.VoucherParaFactura;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;
import org.viajesOnline.utils.Usuario;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GeneradorFacturaTest {

    private GeneradorFactura generadorFactura;
    private ContextoReserva reservaMock;
    private GestorDeDocumentos gestor;
    private Usuario usuarioMock;
    private Solicitud solicitudMock;

    @BeforeEach
    void setUp() {
        generadorFactura = new GeneradorFactura();
        reservaMock = mock(ContextoReserva.class);
        gestor = mock(GestorDeDocumentos.class);
        usuarioMock = mock(Usuario.class);
        solicitudMock = mock(Solicitud.class);

        when(reservaMock.getUsuario()).thenReturn(usuarioMock);
        when(reservaMock.getSolicitud()).thenReturn(solicitudMock);
        when(reservaMock.precioFinalReserva()).thenReturn(5000f);
        when(usuarioMock.getNombre()).thenReturn("Test");
        when(usuarioMock.getDNI()).thenReturn("12345678");
        when(usuarioMock.getEsResponsableInscripto()).thenReturn(false);
    }

    @Test
    void reservaFinalizada() {
        generadorFactura.reservaFinalizada(gestor, reservaMock);

        verify(gestor, times(1)).registrarDocumento(any(ComprobanteFiscal.class));
        verify(gestor, times(1)).registrarDocumento(any(VoucherParaFactura.class));
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
