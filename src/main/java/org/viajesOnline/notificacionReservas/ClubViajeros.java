package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.CuponDescuento;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

import java.util.UUID;

public class ClubViajeros implements ObservadorCambioEstado {

    private MailSender mailSender;

    private String estadoANotificar;

    public ClubViajeros(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void reservaCancelada(ContextoReserva reserva) {

        String codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Documento cupon = new CuponDescuento(codTrans, 5);

        this.enviarMail(reserva, unDocumento);
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
