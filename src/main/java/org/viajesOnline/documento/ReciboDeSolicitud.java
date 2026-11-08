package org.viajesOnline.documento;

public class ReciboDeSolicitud extends Documento{

    private String nombreRecibo;

    public ReciboDeSolicitud(String codigoTransaccion){

        super(codigoTransaccion);
        this.nombreRecibo = "Recibo Solicitud";
    }

    public String tipoDocumento(){return nombreRecibo;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
    }
}
