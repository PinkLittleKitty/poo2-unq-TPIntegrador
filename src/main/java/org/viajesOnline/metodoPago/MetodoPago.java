package org.viajesOnline.metodoPago;

import java.util.UUID;

public abstract class MetodoPago {

    protected double monto;
    protected String codigoTransaccion;

    public MetodoPago(double saldo){this.monto = monto;}

    public final void procesarPago(){

        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado();
    }

    public abstract boolean validarDatos();

    public abstract boolean reservarFondos();

    public abstract void ejecutarTransaccion();

    public void notificarResultado() {

        this.codigoTransaccion = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        System.out.println("Transacción exitosa. Código transacción: " + codigoTransaccion);
    }

    public String getUltimoCodigoTransaccion() {return codigoTransaccion;}

    public double monto(){return monto;}
}

