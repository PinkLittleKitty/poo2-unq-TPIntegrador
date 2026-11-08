package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.documento.ReciboDeSolicitud;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

import java.util.UUID;

public class NotificadorMail implements ObservadorCambioEstado {

    private MailSender mailSender;
    private String codTrans;
    private String estadoANotificar;

    public NotificadorMail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void reservaConfirmada(ContextoReserva reserva) {

        estadoANotificar = "Confirmada";

        codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Documento reciboSolicitud = new ReciboDeSolicitud(codTrans,estadoANotificar);;
        enviarMail(reserva, reciboSolicitud);
    }

    public String estadoANotificar(){return estadoANotificar;}

    @Override
    public void reservaEnCurso(ContextoReserva reserva) {

        estadoANotificar = "En Curso";

        codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Documento reciboSolicitud = new ReciboDeSolicitud(codTrans,estadoANotificar);
        enviarMail(reserva, reciboSolicitud);
    }

    @Override
    public void reservaFinalizada(GestorDeDocumentos gestor, ContextoReserva reserva) {

        estadoANotificar = "Finalizada";

        codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        //Falta agregar los datos pripios de un recibo solicitud
        Documento reciboSolicitud = new ReciboDeSolicitud(codTrans,estadoANotificar);
        enviarMail(reserva, reciboSolicitud);
    }

    private void enviarMail(ContextoReserva reserva, Documento unDocumento) {
        mailSender.enviarMail(
                reserva.getUsuario().getEmail(),
                "Cambio de estado",
                "Tu reserva cambió a: " + this.estadoANotificar(),
                unDocumento);
    }
}