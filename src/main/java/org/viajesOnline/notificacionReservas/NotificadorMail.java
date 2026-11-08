package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

import java.util.UUID;

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

        String codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Documento reciboSolicitud = new ReciboSolicitud(codTrans,monto);
        enviarMail(reserva, reciboSolicitud);
    }

    private void enviarMail(ContextoReserva reserva, Documento unDocumento) {
        mailSender.enviarMail(
                reserva.getUsuario().getEmail(),
                "Cambio de estado",
                "Tu reserva cambió a: " + "Finalizada",
                unDocumento);
                // Hay que añadir el comprobante fiscal y la factura como adjunto al mail.
    }
}