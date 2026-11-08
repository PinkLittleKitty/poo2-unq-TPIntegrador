package org.viajesOnline.documento;


public class ComprobanteFiscal extends Documento implements FormatearComprobante{

    private String nombreComprobante;
    private double monto;

    public ComprobanteFiscal(String codTransaccion,double monto)
    {
        super(codTransaccion);
        this.monto = monto;
        this.nombreComprobante = "Comprobante Fiscal";
    }

    public double monto() { return monto; }

    public String tipoDocumento(){return nombreComprobante;}

    public String obtenerArchivoFormateado(FormatoVisitanteDocumento v){return v.obtenerArchivo(this);}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Total Pagado: $" + this.monto());
    }
}


