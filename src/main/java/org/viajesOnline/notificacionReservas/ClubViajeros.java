package org.viajesOnline.notificacionReservas;

import org.viajesOnline.notificacionReservas.vouchers.CuponDescuento;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.reservas.estados.*;

public class ClubViajeros implements ObservadorCambioEstado {

    @Override
    public void cambioDeEstado(ContextoReserva reserva, EstadoReserva anterior, EstadoReserva nuevo) {
        if (nuevo instanceof Cancelada) {
            CuponDescuento cupon = new CuponDescuento();
            // Enviar al usuario, no sé cómo lol
        }
    }
}
