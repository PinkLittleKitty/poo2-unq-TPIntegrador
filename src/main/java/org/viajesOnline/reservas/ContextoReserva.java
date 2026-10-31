package org.viajesOnline.reservas;

import org.viajesOnline.Catalogo;
import org.viajesOnline.reservas.estados.*;

import java.util.List;

public class ContextoReserva {
    private EstadoReserva estadoActual;
    private List<Catalogo> servicios;
    private Solicitud solicitud =  new Solicitud();

    public EstadoReserva getEstadoActual() {
        return estadoActual;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setEstadoActual(EstadoReserva estadoActual) {
        this.estadoActual = estadoActual;
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
}
