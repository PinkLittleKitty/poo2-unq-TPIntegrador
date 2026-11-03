package org.viajesOnline.utils;

public interface MailSender {
    public void enviarMail(String direcciónDestino, String titulo, String mensaje, Object Adjunto);
}
