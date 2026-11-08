package org.viajesOnline.metodoPago;

public interface ApiBilleteraVirtual {

    public boolean validar(double monto);

    public boolean reservar();

    public double ejecutar(double monto);

    public void notificar();

}
