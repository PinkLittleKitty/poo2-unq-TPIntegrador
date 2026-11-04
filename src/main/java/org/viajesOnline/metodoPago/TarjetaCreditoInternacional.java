package org.viajesOnline.metodoPago;

import org.viajesOnline.exceptions.MetodoPagoExcepcion;

import java.time.LocalDate;

public class TarjetaCreditoInternacional extends MetodoPago {

    private String nroTarjeta;
    private String cvv;
    private LocalDate vencimiento;
    private ApiTarjetaCredito apiTCredito;

    public TarjetaCreditoInternacional(String nroTarjeta,String cvv
                                        ,LocalDate vencimiento,ApiTarjetaCredito apiTCredito)
    {
          this.nroTarjeta = nroTarjeta;
          this.cvv = cvv;
          this.vencimiento = vencimiento;
          this.apiTCredito = apiTCredito;
    }

    @Override
    public boolean validarDatos(){

        if(!apiTCredito.validar(nroTarjeta,cvv,vencimiento)){

           throw new MetodoPagoExcepcion("Tarjeta de credito denegada, no se pudo validar");
        }

        return true;
    }

    @Override
    public boolean reservarFondos(){

        if(!apiTCredito.reservar()){

            throw new MetodoPagoExcepcion("Tarjeta no autorizada por el banco");
        }

        return true;
    }

    @Override
    public double ejecutarTransaccion(){return apiTCredito.ejecutar();}

    @Override
    public void notificarResultado(){

        //voucher impreso / digital (generarlo y registrarlo)
    };

}
