package org.viajesOnline.reservas;

import org.viajesOnline.Catalogo;
import org.viajesOnline.notificacionReservas.ObservadorNotificadorAbstracto;
import org.viajesOnline.notificacionReservas.SujetoEstadoAbstracto;
import org.viajesOnline.notificacionReservas.vouchers.ComprobanteFiscal;
import org.viajesOnline.reservas.estados.*;
import org.viajesOnline.utils.Usuario;

import java.util.List;

public class ContextoReserva <T extends SujetoEstadoAbstracto & EstadoReserva> {

    private T estadoActual;
    private Solicitud solicitud =  new Solicitud();
    private Usuario usuario;
    private ComprobanteFiscal comprobanteFiscal; //TODO: Tipo XML o PDF o algo así, extraer todo lo relacionado a una clase voucer o algo así

    public ContextoReserva(Usuario usuario) {
        this.usuario = usuario;
    }

    public ContextoReserva() {}

    public void setComprobanteFiscal(ComprobanteFiscal comprobanteFiscal) {
        this.comprobanteFiscal = comprobanteFiscal;
    }

    public ComprobanteFiscal getComprobanteFiscal() {
        return comprobanteFiscal;
    }

    public EstadoReserva getEstadoActual() {return this.estadoActual;}

    public Solicitud getSolicitud() {return this.solicitud;}

    public Usuario getUsuario() { return this.usuario; }

    //ES DE TIPO T o sea
    public void setEstadoActual(T estadoActual) {

        this.estadoActual = estadoActual;
        estadoActual.notificarObservadores(this);
    }

    public void siguiente() {
        estadoActual.siguiente(this);
    }

    public void cancelar() {
        estadoActual.cancelar(this);
    }

    public void agregarServicio(Catalogo servicio) {
        estadoActual.agregarServicio(servicio, solicitud);
    }

    public void reembolsar() {
        solicitud.reembolsar();
    }

    public float precioFinalReserva(){
        return (float) solicitud.getServicios().stream().mapToDouble(Catalogo::precioFinal).sum();
    }
}

