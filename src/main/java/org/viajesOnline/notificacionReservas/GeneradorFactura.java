package org.viajesOnline.notificacionReservas;

import org.viajesOnline.documento.*;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.utils.Usuario;

import java.util.UUID;

public class GeneradorFactura implements ObservadorCambioEstado {

    @Override
    public void reservaFinalizada(GestorDeDocumentos gestor, ContextoReserva reserva) {

        String codTransaccion = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Usuario usuario = reserva.getUsuario();
        double total = reserva.precioFinalReserva();
        String tipoFactura = this.tipoFactura(usuario);
        String idFiscal = this.IdFiscal(usuario);
        String nombre = usuario.getNombre();
        int cantServicios = reserva.getSolicitud().cantServicios();

        Documento comprobanteFiscal = new ComprobanteFiscal(codTransaccion, tipoFactura, nombre, idFiscal, total);
        Documento voucherParaFactura = new VoucherParaFactura(codTransaccion, nombre, cantServicios, total);

        gestor.registrarDocumento(comprobanteFiscal);
        gestor.registrarDocumento(voucherParaFactura);
    }

    private boolean esRespInscripto(Usuario usuario) {
        return usuario.getEsResponsableInscripto();
    }

    private String tipoFactura(Usuario usuario) {
        return this.esRespInscripto(usuario) ? "A" : "B";
    }

    private String IdFiscal(Usuario usuario) {
        if (usuario == null) {
            return "Consumidor Final";
        }

        if (this.esRespInscripto(usuario) && usuario.getCUIT() != null) {
            return usuario.getCUIT();
        }

        return usuario.getDNI() != null ? usuario.getDNI() : "Consumidor Final";
    }
}

/*private GestorDeDocumentos gestor;
    private Usuario usuario;
    private int cantServicios;
    private double total;
    private String tipoFactura;
    private String idFiscal;
    private String nombre;

    @Override
    public void reservaFinalizada(GestorDeDocumentos gestor, ContextoReserva reserva) {

        String codTransaccion = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        usuario = reserva.getUsuario();
        total = reserva.precioFinalReserva();
        tipoFactura = this.tipoFactura(usuario);
        idFiscal = this.IdFiscal(usuario);
        cantServicios = reserva.getSolicitud().cantServicios();
        this.gestor = gestor;

        Documento comprobanteFiscal = new ComprobanteFiscal(codTransaccion, tipoFactura, nombre, idFiscal, total);
        Documento voucherParaFactura = new VoucherParaFactura(codTransaccion, nombre, cantServicios, total);

        gestor.registrarDocumento(comprobanteFiscal);
        gestor.registrarDocumento(voucherParaFactura);


    }
*/