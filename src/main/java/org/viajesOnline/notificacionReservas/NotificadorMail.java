package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.*;
import org.viajesOnline.reservas.*;
import java.util.UUID;

public class NotificadorMail extends ObservadorCambioEstado {


    private Documento reciboSolicitud;
    private String codTrans;
    private String estadoANotificar;
    private MailSender mailSender;

    public NotificadorMail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String estadoANotificar(){ return estadoANotificar; }

    @Override
    public void reservaConfirmada(Reserva reserva) {
        notificar(reserva, "Confirmada");
    }

    @Override
    public void reservaEnCurso(Reserva reserva) {
        notificar(reserva, "En Curso");
    }

    @Override
    public void reservaFinalizada(GestorDeDocumentos gestor, Reserva reserva) {
        notificar(reserva, "Finalizada");
    }

    private void notificar(Reserva reserva, String estado) {
        estadoANotificar = estado;
        codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        String email = reserva.getUsuario().getEmail();
        double total = reserva.precioFinalReserva();
        int items = reserva.getDetalle().cantServicios();

        reciboSolicitud = new ReciboDeSolicitud(codTrans, estadoANotificar, email, total, items);
        this.enviarMail(reserva, reciboSolicitud);
    }

    private void enviarMail(Reserva reserva, Documento adjunto) {
        mailSender.enviarMail(
                reserva.getUsuario().getEmail(),
                "Cambio de estado",
                "Tu reserva cambió a: " + this.estadoANotificar(),
                adjunto);
    }
}