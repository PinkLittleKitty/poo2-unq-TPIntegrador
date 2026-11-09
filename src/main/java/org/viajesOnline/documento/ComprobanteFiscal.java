package org.viajesOnline.documento;


import org.viajesOnline.documento.FormatoParaAdjunto.FormatoXml;

public class ComprobanteFiscal extends Documento {

    private String nombreComprobante;
    private double monto;
    private FormatoXml formato;

    public ComprobanteFiscal(String codTransaccion,double monto)
    {
        super(codTransaccion);
        this.monto = monto;
        this.nombreComprobante = "Comprobante Fiscal";
    }

    public double monto() { return monto; }

    public String tipoDocumento(){return nombreComprobante;}

    @Override
    public String darFormato(){return formato.obtenerArchivo(this);}


    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Total Pagado: $" + this.monto());
    }
}


