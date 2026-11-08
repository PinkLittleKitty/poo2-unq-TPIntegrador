package org.viajesOnline.documento;


public class VoucherReembolso extends Documento{

    private String nombreVoucher;

    public VoucherReembolso(String codTransaccion,double monto)
    {
        super(codTransaccion,monto);
        this.nombreVoucher = "Voucher Reembolso";
    }

    public String tipoDocumento(){return nombreVoucher;}

    @Override
    public void mostrarDetalles() {
        // Reutilizamos el comportamiento de la clase padre para no repetir código
        super.mostrarDetalles();
        System.out.println("Tipo Voucher: " + this.tipoDocumento());
    }


}


