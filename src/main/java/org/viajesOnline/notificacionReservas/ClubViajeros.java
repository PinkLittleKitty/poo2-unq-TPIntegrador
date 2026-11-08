package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.CuponDescuento;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

import java.util.UUID;

public class ClubViajeros implements ObservadorCambioEstado {

    @Override
    public void reservaCancelada(ContextoReserva reserva) {
        String codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        CuponDescuento cupon = new CuponDescuento(codTrans, 5);
        // Enviar al usuario
    }
}
