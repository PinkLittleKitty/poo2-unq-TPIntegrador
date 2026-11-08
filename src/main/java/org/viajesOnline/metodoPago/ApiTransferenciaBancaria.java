package org.viajesOnline.metodoPago;

public interface ApiTransferenciaBancaria {

    public boolean validar(String Cbu, String aliasUsuario);

    public boolean reservar();

    public double ejecutar();
}
