package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.CuponDescuento;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

import java.util.UUID;

public class ClubViajeros implements ObservadorCambioEstado {

    private MailSender mailSender;
    private String estadoANotificar;
    private double descuento = 5;

    public ClubViajeros(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void reservaCancelada(ContextoReserva reserva) {

        String codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Documento cupon = new CuponDescuento(codTrans, descuento);

        this.enviarMail(reserva, cupon);
    }

    public double descuento(){return descuento;}

    private void enviarMail(ContextoReserva reserva, Documento unDocumento) {
        mailSender.enviarMail(
                reserva.getUsuario().getEmail(),
                "Cupon de Descuento",
                "Obtuviste un cupon del: " + this.descuento() + "porciento.",
                unDocumento);
    }
}
