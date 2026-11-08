package org.viajesOnline.notificacionReservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;
import org.viajesOnline.utils.MailSender;
import org.viajesOnline.utils.Usuario;

import static org.mockito.Mockito.*;

class NotificadorMailTest {

    private MailSender mailSenderMock;
    private NotificadorMail notificadorMail;
    private ContextoReserva reservaMock;
    private Usuario usuarioMock;
    private Solicitud solicitudMock;

    @BeforeEach
    void setUp() {
        mailSenderMock = mock(MailSender.class);
        notificadorMail = new NotificadorMail(mailSenderMock);
        reservaMock = mock(ContextoReserva.class);
        usuarioMock = mock(Usuario.class);
        solicitudMock = mock(Solicitud.class);

        when(reservaMock.getUsuario()).thenReturn(usuarioMock);
        when(reservaMock.getSolicitud()).thenReturn(solicitudMock);
        when(usuarioMock.getEmail()).thenReturn("viajero@test.com");
    }

    @Test
    void reservaConfirmada() {
        notificadorMail.reservaConfirmada(reservaMock);

        verify(mailSenderMock, times(1)).enviarMail(
                "viajero@test.com",
                "Cambio de estado",
                "Tu reserva cambió a: Confirmada",
                solicitudMock);
    }

    @Test
    void reservaEnCurso() {
        notificadorMail.reservaEnCurso(reservaMock);

        verify(mailSenderMock, times(1)).enviarMail(
                "viajero@test.com",
                "Cambio de estado",
                "Tu reserva cambió a: EnCurso",
                solicitudMock);
    }

    @Test
    void reservaFinalizada() {
        notificadorMail.reservaFinalizada(reservaMock);

        verify(mailSenderMock, times(1)).enviarMail(
                "viajero@test.com",
                "Cambio de estado",
                "Tu reserva cambió a: Finalizada",
                solicitudMock);
    }

    @Test
    void eventosNoSuscritos() {
        notificadorMail.reservaPendiente(reservaMock);
        notificadorMail.reservaEnCheckin(reservaMock);
        notificadorMail.reservaCancelada(reservaMock);

        verifyNoInteractions(mailSenderMock);
    }
}
