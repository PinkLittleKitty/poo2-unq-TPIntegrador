package org.viajesOnline.metodoPago;

import java.time.LocalDate;

public class TarjetaCreditoInternacional extends MetodoPago {

    private String nroTarjeta;
    private String cvv;
    private LocalDate vencimiento;
    private ApiTarjetaCredito apiTCredito;

    public TarjetaCreditoInternacional(String nroTarjeta,String cvv,LocalDate vecimiento, ApiTarjetaCredito apiTCredito){}

    @Override
    public boolean validarDatos(){};

    @Override
    public boolean reservarFondos(){return false;};

    @Override
    public double ejecutarTransaccion(){return 0;};

    @Override
    public void notificarResultado(){};

}
