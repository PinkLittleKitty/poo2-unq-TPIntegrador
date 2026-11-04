package org.viajesOnline.notificacionReservas;


import org.viajesOnline.reservas.*;
import org.viajesOnline.reservas.estados.*;
import org.viajesOnline.utils.MailSender;

class NotificadorMail implements ObservadorCambioEstado {

    private MailSender mailSender;

    public NotificadorMail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void cambioDeEstado(ContextoReserva reserva, EstadoReserva anterior, EstadoReserva nuevo) {
        if (nuevo instanceof Confirmada || nuevo instanceof EnCurso || nuevo instanceof Finalizada) {
            mailSender.enviarMail(reserva.getUsuario().getEmail(), "Cambio de estado", "Tu reserva cambió a: " + nuevo.getClass().getSimpleName(), reserva.getSolicitud());
        }
    }
}

