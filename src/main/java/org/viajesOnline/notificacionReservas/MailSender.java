package org.viajesOnline.notificacionReservas;
import org.viajesOnline.documento.Documento;

public interface MailSender {
    public void enviarMail(String direcciónDestino, String titulo, String mensaje, Object Adjunto);
}
