package org.viajesOnline.notificacionReservas;


import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.MailSender;

class NotificadorMail implements ObservadorNotificadorAbstracto {

    private MailSender MS;

    public NotificadorMail(MailSender mailSender) {
        this.MS = mailSender;
    }

    public void recibirNotificacion(ContextoReserva r){
        MS.enviarMail(r.getUsuario().getEmail(), "Cambio de estado en tu reserva", "Tu reserva cambió a " + r.getEstadoActual(), r.getSolicitud());
    }
}

