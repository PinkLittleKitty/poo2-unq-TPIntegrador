package org.viajesOnline.documento;


public class ComprobanteFiscal extends Documento implements FormatearComprobante{

    private String nombreComprobante;
    private String documentoFormateado;

    public ComprobanteFiscal(String codTransaccion,double monto)
    {
        super(codTransaccion,monto);
        this.nombreComprobante = "Comprobante Fiscal";
    }

    public String tipoDocumento(){return nombreComprobante;}

    public String obtenerArchivoFormateado(FormatoVisitanteDocumento v){return v.obtenerArchivo(this);}

}


