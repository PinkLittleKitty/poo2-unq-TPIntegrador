package org.viajesOnline.documento;

public class ReciboDeSolicitud extends Documento{

    private String nombreRecibo;
    private String estadoDeSolicitud;

    public ReciboDeSolicitud(String codigoTransaccion,String estadoDeSolicitud){

        super(codigoTransaccion);
        this.nombreRecibo = "Recibo Solicitud";
        this.estadoDeSolicitud = estadoDeSolicitud;
    }

    public String estadoDeSolicitud(){return estadoDeSolicitud;}

    public String tipoDocumento(){return nombreRecibo;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Estado Solicitud: " + this.estadoDeSolicitud());
    }
}
