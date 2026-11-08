package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.*;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

import java.util.UUID;

public class GeneradorFactura implements ObservadorCambioEstado {

    @Override
    public void reservaFinalizada(GestorDeDocumentos gestor, ContextoReserva reserva) {

        String codTransaccion = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        ////Falta agregar los datos propios de un comprobante fiscal y de una factura
        Documento comprobanteFiscal = new ComprobanteFiscal(codTransaccion, 100);
        Documento voucherParaFactura = new VoucherParaFactura(codTransaccion, 100);
        gestor.registrarDocumento(comprobanteFiscal);
        gestor.registrarDocumento(voucherParaFactura);
    }
}
