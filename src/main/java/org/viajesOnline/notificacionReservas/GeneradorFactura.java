package org.viajesOnline.notificacionReservas;


import org.viajesOnline.notificacionReservas.vouchers.ComprobanteFiscalBis;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.Usuario;

class GeneradorFactura implements ObservadorNotificadorAbstracto {
    //TODO: Posible visitor XML.
    public void recibirNotificacion(ContextoReserva r){
        // Esto quizá debería guardarlo el contextoReserva
        Usuario usuario = r.getUsuario();
        ComprobanteFiscalBis comprobante = new ComprobanteFiscalBis(); //usuario.getNombre(), usuario.getEmail(), usuario.getCUIT(), usuario.getDNI(), usuario.getDomicilio(), usuario.getEsResponsableInscripto().toString(), r.getTasas()); //how tf funca el stringbuilder
        r.setVoucher(comprobante);
    }
}

