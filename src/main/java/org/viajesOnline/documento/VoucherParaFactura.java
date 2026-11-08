package org.viajesOnline.documento;


public class VoucherParaFactura extends Documento{

     private String nombreVoucher;

    // ESTO VA DENTRO DEL CONSTUCTOR this.nombreVoucher = "Voucher Factura";

    public VoucherParaFactura(String codTransaccion,double monto)
    {
        super(codTransaccion,monto);
        this.nombreVoucher = "Voucher Factura";
    }

    public String tipoDocumento(){return nombreVoucher;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
    }


}
