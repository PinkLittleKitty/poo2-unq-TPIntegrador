package org.viajesOnline.gestorDeRecibos;

import java.time.LocalDateTime;

public abstract class Voucher {

    private String codTransaccion;
    private String emisor = "Empresa Viajes";
    private LocalDateTime fechaEmisionVoucher;
    protected String archivoParaRegistro;
    protected double monto;

    public abstract void accept(FormatoVisitante v);

    public String archivoConFormato(){return archivoParaRegistro;}

    public Voucher(String codTransaccion,double monto) {

        this.monto = monto;
        this.codTransaccion = codTransaccion;
        this.fechaEmisionVoucher = LocalDateTime.now(); // Se genera automáticamente al crearse
    }

    public String codTransaccion() { return codTransaccion; }

    public double monto() { return monto; }

    public String emisor() { return emisor; }

    public LocalDateTime fechaEmision() { return fechaEmisionVoucher; }

    public void mostrarDetalles() {
        System.out.println("Codigo Transaccion: " + codTransaccion);
        System.out.println("Emisor: " + emisor);
        System.out.println("Fecha: " + fechaEmisionVoucher);
    }
}
