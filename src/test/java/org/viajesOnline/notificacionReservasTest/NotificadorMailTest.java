package org.viajesOnline.notificacionReservasTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.notificacionReservas.MailSender;
import org.viajesOnline.notificacionReservas.NotificadorMail;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;
import org.viajesOnline.utils.Usuario;

import static org.mockito.Mockito.*;

class NotificadorMailTest {

    private MailSender mailSenderMock;
    private NotificadorMail notificadorMail;
    private ContextoReserva reservaMock;
    private Usuario usuarioMock;
    private Solicitud solicitudMock;
    private GestorDeDocumentos gestor;

    @BeforeEach
    void setUp() {
        mailSenderMock = mock(MailSender.class);
        notificadorMail = new NotificadorMail(mailSenderMock);
        reservaMock = mock(ContextoReserva.class);
        usuarioMock = mock(Usuario.class);
        solicitudMock = mock(Solicitud.class);
        gestor = mock(GestorDeDocumentos.class);

        when(reservaMock.getUsuario()).thenReturn(usuarioMock);
        when(reservaMock.getSolicitud()).thenReturn(solicitudMock);
        when(usuarioMock.getEmail()).thenReturn("viajero@test.com");
    }

    @Test
    void reservaConfirmada() {
        notificadorMail.reservaConfirmada(reservaMock);

        verify(mailSenderMock, times(1)).enviarMail(
                eq("viajero@test.com"),
                eq("Cambio de estado"),
                eq("Tu reserva cambió a: Confirmada"),
                any(Documento.class));
    }

    @Test
    void reservaEnCurso() {
        notificadorMail.reservaEnCurso(reservaMock);

        verify(mailSenderMock, times(1)).enviarMail(
                eq("viajero@test.com"),
                eq("Cambio de estado"),
                eq("Tu reserva cambió a: En Curso"),
                any(Documento.class));
    }

    @Test
    void reservaFinalizada() {
        notificadorMail.reservaFinalizada(gestor, reservaMock);

        verify(mailSenderMock, times(1)).enviarMail(
                eq("viajero@test.com"),
                eq("Cambio de estado"),
                eq("Tu reserva cambió a: Finalizada"),
                any(Documento.class));
    }

    @Test
    void eventosNoSuscritos() {
        notificadorMail.reservaPendiente(reservaMock);
        notificadorMail.reservaEnCheckin(reservaMock);
        notificadorMail.reservaCancelada(reservaMock);

        verifyNoInteractions(mailSenderMock);
    }
}
