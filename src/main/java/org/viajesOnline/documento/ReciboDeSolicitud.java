package org.viajesOnline.documento;

import org.viajesOnline.documento.FormatoParaAdjunto.FormatoAdjunto;
import org.viajesOnline.documento.FormatoParaAdjunto.FormatoXml;

public class ReciboDeSolicitud extends Documento{

    private FormatoAdjunto formato;

    //private FormatoXml formato;
    private String nombreRecibo;
    private String estadoDeSolicitud;

    public ReciboDeSolicitud(String codigoTransaccion,String estadoDeSolicitud,
                             FormatoAdjunto formato){

        super(codigoTransaccion);
        FormatoAdjunto formato = formato;
        this.nombreRecibo = "Recibo Solicitud";
        this.estadoDeSolicitud = estadoDeSolicitud;
    }

    @Override
    public String darFormato(){return formato.obtenerArchivo(this);}

    public String estadoDeSolicitud(){return estadoDeSolicitud;}

    public String tipoDocumento(){return nombreRecibo;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Estado Solicitud: " + this.estadoDeSolicitud());
    }

}
