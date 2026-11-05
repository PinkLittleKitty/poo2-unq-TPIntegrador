package org.viajesOnline.metodoPago;


import org.viajesOnline.exceptions.MetodoPagoExcepcion;
import org.viajesOnline.gestorDeRecibos.ComprobanteCbu;
import org.viajesOnline.gestorDeRecibos.GestorDeRecibos;

public class MetodoPagoTransferenciaBancaria extends MetodoPago{

    //private double monto;
    private String cbu;
    private String aliasUsuario;
    private ApiTransferenciaBancaria apiTBancaria;
    private GestorDeRecibos gestor;

    public MetodoPagoTransferenciaBancaria(GestorDeRecibos gestor,Double monto,String cbu,String aliasUsuario
                                           ,ApiTransferenciaBancaria apiTBancaria)
    {
        super(monto);
        this.gestor = gestor;
        this.aliasUsuario = aliasUsuario;
        this.cbu = cbu;
        this.apiTBancaria = apiTBancaria;
    }

    @Override
    public boolean validarDatos(){

        if(!apiTBancaria.validar(cbu,aliasUsuario)){

            throw new MetodoPagoExcepcion("Transferencia bancaria denegada, no se pudo validar alias / cbu");
        }

        return true;
    }

    @Override
    public boolean reservarFondos(){

        if(!apiTBancaria.reservar()){

            throw new MetodoPagoExcepcion("Denegada no aplica transferencia directa");
        }

        return true;
    }

    @Override
    public void ejecutarTransaccion(){monto = apiTBancaria.ejecutar();}

    @Override
    public void notificarResultado() {

        super.notificarResultado();
        ComprobanteCbu nuevoComprobanteCbu = this.generarVoucher();
        gestor.registrarComprobanteCbu(nuevoComprobanteCbu);
    }

    public ComprobanteCbu generarVoucher() {

        return new ComprobanteCbu(ultimoCodigoTransaccion,monto,cbu,aliasUsuario);
    }

    //public double monto(){return monto;}
}
