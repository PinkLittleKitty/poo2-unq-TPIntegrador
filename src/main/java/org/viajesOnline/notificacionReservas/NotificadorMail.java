package org.viajesOnline.notificacionReservas;


import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.MailSender;

class NotificadorMail implements ObservadorNotificadorAbstracto, MailSender {

    public void recibirNotificacion(ContextoReserva r){
        this.enviarMail(r.getEmail(), "Cambio de estado en tu reserva", "Tu reserva cambió a " + r.getEstadoActual(), r.getSolicitud());
    }

    @Override
    public void enviarMail(String direcciónDestino, String titulo, String mensaje, Object Adjunto) {}
}

