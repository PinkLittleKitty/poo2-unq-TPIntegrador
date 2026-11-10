package org.viajesOnline.documento;

public class CuponDescuento extends Documento {

    private double porcentajeDescuento;
    private String nombreVoucher;

    public CuponDescuento(String codTrans, double porcentajeDescuento)
    {
        super(codTrans);
        this.porcentajeDescuento = porcentajeDescuento;
        this.nombreVoucher = "Cupon Descuento";
    }

    public double porcentajeDescuento() {
        return porcentajeDescuento;
    }

    public String tipoDocumento(){return nombreVoucher;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Descuento Obtenido: " + this.porcentajeDescuento());
    }


}
