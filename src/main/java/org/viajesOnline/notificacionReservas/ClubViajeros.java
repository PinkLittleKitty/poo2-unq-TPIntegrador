package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.CuponDescuento;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class ClubViajeros implements ObservadorCambioEstado {

    @Override
    public void reservaCancelada(ContextoReserva reserva) {
        CuponDescuento cupon = new CuponDescuento(5);
        // Enviar al usuario
    }
}
