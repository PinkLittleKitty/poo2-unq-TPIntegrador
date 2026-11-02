package org.viajesOnline.notificacionReservas;


import org.viajesOnline.notificacionReservas.vouchers.ComprobanteFiscal;
import org.viajesOnline.reporte.FormatoVisitante;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.Usuario;

class GeneradorFactura implements ObservadorNotificadorAbstracto {
    //TODO: Posible visitor XML.
    public void recibirNotificacion(ContextoReserva r){
        // Esto quizá debería guardarlo el contextoReserva
        Usuario usuario = r.getUsuario();
        ComprobanteFiscal comprobante = new ComprobanteFiscal(usuario.getNombre(), usuario.getEmail(), usuario.getCUIT(), usuario.getDNI(), usuario.getDomicilio(), usuario.getEsResponsableInscripto().toString(), r.getTasas()); //how tf funca el stringbuilder
        r.setComprobanteFiscal(comprobante);
    }
}

