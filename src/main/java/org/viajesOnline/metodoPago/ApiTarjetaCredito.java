package org.viajesOnline.metodoPago;

public interface ApiTarjetaCredito {

    @Override
    public void notificarResultado();

        // voucher impreso / digital
        //(generarlo y registrarlo)


    @Override
    public abstract boolean validarDatos( );

    @Override
    public abstract void reservarFondos();

    @Override
    public abstract void ejecutarTransaccion();
}
