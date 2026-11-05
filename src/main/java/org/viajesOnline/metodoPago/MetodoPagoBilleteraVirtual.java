package org.viajesOnline.metodoPago;

import org.viajesOnline.exceptions.MetodoPagoExcepcion;
import org.viajesOnline.gestorDeRecibos.ComprobanteCbu;
import org.viajesOnline.gestorDeRecibos.GestorDeRecibos;

public class MetodoPagoBilleteraVirtual extends MetodoPago {

    private ApiBilleteraVirtual apiBVirtual;

    //private double saldo;

    public MetodoPagoBilleteraVirtual(Double monto,ApiBilleteraVirtual apiBVirtual)
    {
        super(monto);
        //this.saldo = saldo;
        this.apiBVirtual = apiBVirtual;
    }

    @Override
    public boolean validarDatos(){

        if(!apiBVirtual.validar(monto)){

            throw new MetodoPagoExcepcion("Transferencia denegada, saldo insuficiente en cuenta");
        }

        return true;
    }

    @Override
    public boolean reservarFondos(){

        if(!apiBVirtual.reservar()){

            throw new MetodoPagoExcepcion("Bloqueo del saldo hasta confirmar");
        }

        return true;
    }

    @Override
    public void ejecutarTransaccion(){apiBVirtual.ejecutar(monto);}

    @Override
    public void notificarResultado() {apiBVirtual.notificar();}

}
