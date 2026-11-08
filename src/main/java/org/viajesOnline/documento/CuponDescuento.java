package org.viajesOnline.documento;

import java.util.UUID;

public class CuponDescuento extends Documento implements FormatearComprobante {

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

    public String obtenerArchivoFormateado(FormatoVisitanteDocumento v){return v.obtenerArchivo(this);}

    public String tipoDocumento(){return nombreVoucher;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Descuento Obtenido: " + this.porcentajeDescuento());
    }


}
