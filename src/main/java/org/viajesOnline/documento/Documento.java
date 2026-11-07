package org.viajesOnline.documento;

import java.time.LocalDateTime;

public abstract class Documento{

    //QUIZAS FALTE PASARLE EL RECEPTOR
    private String codTransaccion;
    private String emisor = "Empresa Turismo";
    private LocalDateTime fechaEmisionVoucher;
    protected double monto;

    public Documento(String codTransaccion, double monto) {

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
        System.out.println("Total Pagado: $" + monto);
    }
}
