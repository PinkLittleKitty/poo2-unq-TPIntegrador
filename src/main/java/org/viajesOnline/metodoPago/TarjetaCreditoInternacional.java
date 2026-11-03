package org.viajesOnline.metodoPago;

import java.time.LocalDate;

public class TarjetaCreditoInternacional extends MetodoPago {

    private String nroTarjeta;
    private String cvv;
    private LocalDate vencimiento;
    private ApiTarjetaCredito apiTCredito;

    public TarjetaCreditoInternacional(String nroTarjeta,String cvv
                                      ,LocalDate vencimiento,ApiTarjetaCredito apiTCredito){

        this.nroTarjeta = nroTarjeta;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
        this.apiTCredito = apiTCredito;
    }

    @Override
    public void notificarResultado() {

        // voucher impreso / digital
        //(generarlo y registrarlo)
    }

    @Override
    public boolean validarDatos(){

    }

    @Override
    public void reservarFondos(){};

    @Override
    public void ejecutarTransaccion(){};

}
