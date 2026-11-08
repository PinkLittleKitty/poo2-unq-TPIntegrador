package org.viajesOnline.notificacionReservas;

public interface MailSender {
    public void enviarMail(String direcciónDestino, String titulo, String mensaje,Documento Adjunto);
}
