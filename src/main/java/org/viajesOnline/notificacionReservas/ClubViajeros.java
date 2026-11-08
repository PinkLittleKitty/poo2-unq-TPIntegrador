package org.viajesOnline.notificacionReservas;

import org.viajesOnline.notificacionReservas.vouchers.CuponDescuento;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class ClubViajeros implements ObservadorCambioEstado {

    @Override
    public void reservaCancelada(ContextoReserva reserva) {
        CuponDescuento cupon = new CuponDescuento();
        // Enviar al usuario
    }
}
