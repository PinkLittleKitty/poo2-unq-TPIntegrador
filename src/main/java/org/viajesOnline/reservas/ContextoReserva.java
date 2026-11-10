package org.viajesOnline.reservas;

import org.viajesOnline.Catalogo;
import org.viajesOnline.documento.*;
import org.viajesOnline.reservas.estados.*;
import org.viajesOnline.utils.*;

import java.time.LocalDate;
import java.util.*;

public class ContextoReserva {

    private EstadoReserva estadoActual;
    private List<ObservadorCambioEstado> observadores = new ArrayList<>();
    private Solicitud solicitud =  new Solicitud();
    private Usuario usuario;
    private GestorDeDocumentos gestor;
    private Periodo periodo;
    private LocalDate fechaCompra;

    public ContextoReserva(GestorDeDocumentos gestor, Usuario usuario, Periodo periodo) {
        this.gestor = gestor;
        this.usuario = usuario;
        this.periodo = periodo;
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
        observadores.forEach(observador -> nuevo.notificar(gestor, this, observador));
    }

    public EstadoReserva getEstadoActual() {return this.estadoActual;}

    public Solicitud getSolicitud() {return this.solicitud;}

    public Usuario getUsuario() { return this.usuario; }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Periodo getPeriodo() {
        return periodo;
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
        double tasas = this.estadoActual.tasasReembolsables(this);
        double costoServicios = this.solicitud.precioTotalServicios();
        String cod = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Documento voucher = new VoucherReembolso(cod, costoServicios, tasas);
        this.gestor.registrarDocumento(voucher);
    }

    public double getCostoTasas() {
        return 100.0; //TODO: Revisar cuánto costarían las tasas, por ahora lo pongo en 100.
    }

    public float precioFinalReserva(){
        return (float) solicitud.getServicios().stream().mapToDouble(Catalogo::precioFinal).sum();
    }
}
