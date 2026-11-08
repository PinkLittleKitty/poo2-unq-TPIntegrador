package org.viajesOnline.documento;


public class VoucherParaPago extends Documento {

    private String nroTarjeta;
    private String nombreVoucher;
    private double monto;

    public VoucherParaPago(String codTransaccion,String nroTarjeta,String codigoAutorizacion,double monto){

        super(codTransaccion);
        this.monto = monto;
        this.nroTarjeta = nroTarjeta;
        this.nombreVoucher = "Voucher Pago";
    }

    public double monto() { return monto; }

    public String numeroTarjeta(){return nroTarjeta;}

    public String tipoDocumento(){return nombreVoucher;}

    @Override
    public void mostrarDetalles() {
        // Reutilizamos el comportamiento de la clase padre para no repetir código
        super.mostrarDetalles();
        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Numero Tarjeta: " + this.numeroTarjeta());
    }

}
