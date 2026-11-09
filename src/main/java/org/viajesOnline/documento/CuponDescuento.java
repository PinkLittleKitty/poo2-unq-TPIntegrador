package org.viajesOnline.documento;

import org.viajesOnline.documento.FormatoParaAdjunto.FormatoXml;

public class CuponDescuento extends Documento {

    private FormatoXml formato;
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


    @Override
    public String darFormato(){return formato.obtenerArchivo(this);}


    public String tipoDocumento(){return nombreVoucher;}

    @Override
    public void mostrarDetalles() {

        System.out.println("Tipo Voucher: " + this.tipoDocumento());
        System.out.println("Descuento Obtenido: " + this.porcentajeDescuento());
    }


}
