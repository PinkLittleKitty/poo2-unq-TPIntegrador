package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.ComprobanteFiscal;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

import java.util.UUID;

public class GeneradorFactura implements ObservadorCambioEstado {

    @Override
    public void reservaFinalizada(GestorDeDocumentos gestor, ContextoReserva reserva) {
        String codTransaccion = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Documento comprobante = new ComprobanteFiscal(codTransaccion, 100);
        gestor.registrarDocumento(comprobante);
    }
}
