package org.viajesOnline.reservas;

import org.viajesOnline.Catalogo;
import org.viajesOnline.notificacionReservas.SujetoEstadoAbstracto;
import org.viajesOnline.notificacionReservas.vouchers.Voucher;
import org.viajesOnline.reservas.estados.*;
import org.viajesOnline.utils.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ContextoReserva {

    private EstadoReserva estadoActual;
    private List<ObservadorCambioEstado> observadores = new ArrayList<>();
    private Solicitud solicitud =  new Solicitud();
    private Usuario usuario;
    private Voucher voucher; //TODO: Tipo XML o PDF o algo así, extraer todo lo relacionado a una clase voucher o algo así

    public ContextoReserva(Usuario usuario) {
        this.usuario = usuario;
    }

    public ContextoReserva() {}

    public void agregarObservador(ObservadorCambioEstado observador) {
        this.observadores.add(observador);
    }

    public void quitarObservador(ObservadorCambioEstado observador) {
        this.observadores.remove(observador);
    }

    public void setEstadoActual(EstadoReserva nuevoEstado) {
        EstadoReserva estadoAnterior = this.estadoActual;
        this.estadoActual = nuevoEstado;
        this.notificarObservadores(nuevoEstado);
    }

    private void notificarObservadores(EstadoReserva nuevo) {
        observadores.forEach(observador -> nuevo.notificar(this, observador));
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public EstadoReserva getEstadoActual() {return this.estadoActual;}

    public Solicitud getSolicitud() {return this.solicitud;}

    public Usuario getUsuario() { return this.usuario; }

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

    public Object getTasas() {
        return null;
    }
}
