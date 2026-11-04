package org.viajesOnline.notificacionReservas;


import org.viajesOnline.notificacionReservas.vouchers.ComprobanteFiscal;
import org.viajesOnline.reporte.FormatoVisitante;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.reservas.estados.EstadoReserva;
import org.viajesOnline.reservas.estados.Finalizada;
import org.viajesOnline.utils.Usuario;

class GeneradorFactura implements ObservadorCambioEstado {
    //TODO: Posible visitor XML.

    @Override
    public void cambioDeEstado(ContextoReserva reserva, EstadoReserva anterior, EstadoReserva nuevo) {
        if (nuevo instanceof Finalizada) {
            ComprobanteFiscal comprobante = new ComprobanteFiscal();
            reserva.setVoucher(comprobante);
        }
    }
}

