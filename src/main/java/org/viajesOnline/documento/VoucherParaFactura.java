package org.viajesOnline.documento;


public class VoucherParaFactura extends Documento{

    private String nombreVoucher;
    private double monto;

    public VoucherParaFactura(String codTransaccion,double monto)
    {
        super(codTransaccion);
        this.monto = monto;
        this.nombreVoucher = "Voucher Factura";
    }

    public double monto() { return monto; }

    public String tipoDocumento(){return nombreVoucher;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Total Pagado: $" + this.monto());
    }


}
