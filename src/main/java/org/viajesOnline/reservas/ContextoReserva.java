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

    public void setEstadoActual(EstadoReserva estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void siguiente() {
        estadoActual = estadoActual.siguiente();
    }

    public void cancelar() {
        estadoActual = estadoActual.cancelar();
    }

    public void agregarServicio(Catalogo servicio) {
        estadoActual.agregarServicio(servicio, solicitud);
    }
}
