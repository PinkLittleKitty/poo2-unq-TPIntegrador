package org.viajesOnline.documento;

public class CuponDescuento extends Documento {

    private double porcentajeDescuento;
    private String nombreCupones;

    public CuponDescuento(String codTrans, double porcentajeDescuento)
    {
        super(codTrans);
        this.porcentajeDescuento = porcentajeDescuento;
        this.nombreCupones = "Cupon Descuento";
    }

    public double porcentajeDescuento() {
        return porcentajeDescuento;
    }

    public String tipoDocumento(){return nombreCupones;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Descuento Obtenido: " + this.porcentajeDescuento());
    }


}
