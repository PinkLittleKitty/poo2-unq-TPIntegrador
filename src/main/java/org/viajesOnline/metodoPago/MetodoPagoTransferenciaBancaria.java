package org.viajesOnline.metodoPago;

import org.viajesOnline.documento.Documento;
import org.viajesOnline.exceptions.MetodoPagoExcepcion;
import org.viajesOnline.documento.ComprobanteCbu;
import org.viajesOnline.documento.GestorDeDocumentos;

public class MetodoPagoTransferenciaBancaria extends MetodoPago{


    private String cbu;
    private String aliasUsuario;
    private ApiTransferenciaBancaria apiTBancaria;
    private GestorDeDocumentos gestor;

    public MetodoPagoTransferenciaBancaria(GestorDeDocumentos gestor, Double monto, String cbu, String aliasUsuario
                                           , ApiTransferenciaBancaria apiTBancaria)
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
        Documento nuevoComprobanteCbu = this.generarVoucher();
        gestor.registrarDocumento(nuevoComprobanteCbu);
    }

    public Documento generarVoucher() {

        return new ComprobanteCbu(codigoTransaccion,monto,cbu,aliasUsuario);
    }

}
