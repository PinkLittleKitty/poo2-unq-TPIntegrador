package org.viajesOnline.gestorDeRecibos;


public class VoucherParaPago extends Recibo {

    private double monto;
    private String nroTarjeta;
    private String codigoAutorizacion;

    /*
    public void accept(FormatoVisitante v){

        v.visitar(this);
        archivoParaRegistro = v.obtenerArchivoConFormato();
    }*/

    public VoucherParaPago(String codTransaccion,String nroTarjeta,String codigoAutorizacion,double monto){

        super(codTransaccion,monto);
        this.nroTarjeta = nroTarjeta;
    }

    public String numeroTarjeta(){return nroTarjeta;}

    public String codigoAutorizacion(){return codigoAutorizacion;}

    @Override
    public void mostrarDetalles() {
        // Reutilizamos el comportamiento de la clase padre para no repetir código
        super.mostrarDetalles();
        System.out.println("Codigo Autorización: " + codigoAutorizacion);
        System.out.println("numero Tarjeta: " + nroTarjeta);
    }

}
