package org.viajesOnline.notificacionReservas;

import org.viajesOnline.notificacionReservas.vouchers.ComprobanteFiscal;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class GeneradorFactura implements ObservadorCambioEstado {

    @Override
    public void reservaFinalizada(ContextoReserva reserva) {
        ComprobanteFiscal comprobante = new ComprobanteFiscal();
        reserva.setVoucher(comprobante);
    }
}
