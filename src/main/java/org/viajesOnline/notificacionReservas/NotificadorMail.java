package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class NotificadorMail implements ObservadorCambioEstado {

    private MailSender mailSender;

    public NotificadorMail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void reservaConfirmada(ContextoReserva reserva) {
        enviarMail(reserva, "Confirmada");
    }

    @Override
    public void reservaEnCurso(ContextoReserva reserva) {
        enviarMail(reserva, "EnCurso");
    }

    @Override
    public void reservaFinalizada(GestorDeDocumentos gestor, ContextoReserva reserva) {
        enviarMail(reserva, "Finalizada");
    }

    private void enviarMail(ContextoReserva reserva, String estado) {
        mailSender.enviarMail(
                reserva.getUsuario().getEmail(),
                "Cambio de estado",
                "Tu reserva cambió a: " + estado,
                reserva.getSolicitud());
                // Hay que añadir el comprobante fiscal y la factura como adjunto al mail.
    }
}