package org.viajesOnline.notificacionReservas;

public abstract class Mail {

    protected MailSender mailSender;

    public Mail(MailSender mailSender) {

        this.setMail(mailSender);
    }

    public void setMail(MailSender mailSender){this.mailSender = mailSender;;}
}
