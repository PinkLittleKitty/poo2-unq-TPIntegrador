package org.viajesOnline.documento;

import java.time.LocalDateTime;

public abstract class Documento{

    private String codTransaccion;
    private String emisor = "Empresa Turismo";
    private LocalDateTime fechaEmisionVoucher;

    public Documento(String codTransaccion) {

        this.codTransaccion = codTransaccion;
        this.fechaEmisionVoucher = LocalDateTime.now(); // Se genera automáticamente al crearse
    }

    public String codTransaccion() { return codTransaccion; }

    public String emisor() { return emisor; }

    public LocalDateTime fechaEmision() { return fechaEmisionVoucher; }

    public void mostrarDetalles() {
        System.out.println("Codigo Transaccion: " + this.codTransaccion());
        System.out.println("Emisor: " + this.emisor());
        System.out.println("Fecha: " + this.fechaEmision());
    }
}
