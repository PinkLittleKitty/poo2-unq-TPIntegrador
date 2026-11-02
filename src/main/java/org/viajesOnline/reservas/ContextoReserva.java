package org.viajesOnline.reservas;

import org.viajesOnline.Catalogo;
import org.viajesOnline.notificacionReservas.ObservadorNotificadorAbstracto;
import org.viajesOnline.notificacionReservas.SujetoEstadoAbstracto;
import org.viajesOnline.reservas.estados.*;

import java.util.List;


/*
public class ContextoReserva {

    private EstadoReserva estadoActual;
    private List<Catalogo> servicios;
    private Solicitud solicitud =  new Solicitud();

    public EstadoReserva getEstadoActual() {return this.estadoActual;}

    public Solicitud getSolicitud() {return solicitud;}

    public void setEstadoActual(EstadoReserva estadoActual) {

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

    public double precioFinalReserva(){


        //sumar todos los precios finales de los servicios reservados
        //sum
        return 0;
    }
}
*/



public class ContextoReserva <T extends SujetoEstadoAbstracto & EstadoReserva> {

    private T estadoActual;
    //private List<Catalogo> servicios;
    private Solicitud solicitud =  new Solicitud();

    public EstadoReserva getEstadoActual() {return this.estadoActual;}

    public Solicitud getSolicitud() {return solicitud;}

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

    public double precioFinalReserva(){

        //sumar todos los precios finales de los servicios reservados
        //sum solicitud.getServicios();
        return 0;
    }
}

