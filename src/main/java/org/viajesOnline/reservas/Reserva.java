package org.viajesOnline.reservas;

import org.viajesOnline.items.Item;
import org.viajesOnline.documento.*;
import org.viajesOnline.reservas.estados.*;
import org.viajesOnline.utils.*;

import java.time.LocalDate;
import java.util.*;

public class Reserva {

    private EstadoReserva estadoActual = new SolicitudPendiente();
    private List<ObservadorCambioEstado> observadores = new ArrayList<>();
    private DetalleReserva detalleReserva = new DetalleReserva();
    private Usuario usuario;
    private GestorDeDocumentos gestor;
    private Periodo periodo;
    private LocalDate fechaCompra;

    public Reserva(GestorDeDocumentos gestor, Usuario usuario, Periodo periodo) {
        this.gestor = gestor;
        this.usuario = usuario;
        this.periodo = periodo;
    }

    public void agregarObservador(ObservadorCambioEstado observador) {
        this.observadores.add(observador);
    }

    public void quitarObservador(ObservadorCambioEstado observador) {
        this.observadores.remove(observador);
    }

    public void setEstadoActual(EstadoReserva nuevoEstado) {
        this.estadoActual = nuevoEstado;
        this.notificarObservadores(nuevoEstado);
    }

    private void notificarObservadores(EstadoReserva nuevo) {
        observadores.forEach(observador -> nuevo.notificar(gestor, this, observador));
    }

    public EstadoReserva getEstadoActual() {
        return this.estadoActual;
    }

    public DetalleReserva getDetalle() {
        return this.detalleReserva;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

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

    public void agregarServicio(Item servicio) {
        estadoActual.agregarServicio(servicio, detalleReserva);
    }

    public void reembolsar() {
        double tasas = this.estadoActual.tasasReembolsables(this);
        double costoServicios = this.detalleReserva.precioTotalServicios();
        String cod = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Documento voucher = new VoucherReembolso(cod, costoServicios, tasas);
        this.gestor.registrarDocumento(voucher);
    }

    public double getCostoTasas() {
        return 100.0; // TODO: Revisar cuánto costarían las tasas, por ahora lo pongo en 100.
    }

    public float precioFinalReserva() {
        return (float) detalleReserva.getServicios().stream().mapToDouble(Item::precioFinal).sum();
    }

    public void decrementarCupo() {
        this.detalleReserva.decrementarCupo(this.periodo);
    }

    public void incrementarCupo() {
        this.detalleReserva.incrementarCupo(this.periodo);
    }
}
