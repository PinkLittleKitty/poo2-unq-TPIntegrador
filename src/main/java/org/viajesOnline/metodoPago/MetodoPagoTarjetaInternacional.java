package org.viajesOnline.metodoPago;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.exceptions.MetodoPagoExcepcion;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.documento.VoucherParaPago;

import java.time.YearMonth;

public class MetodoPagoTarjetaInternacional extends MetodoPago {

    private String nroTarjeta;
    private String cvv;
    private YearMonth vencimiento;
    private ApiTarjetaCredito apiTCredito;
    private GestorDeDocumentos gestor;
    public MetodoPagoTarjetaInternacional(GestorDeDocumentos gestor, Double monto, String nroTarjeta, String cvv
                                        , YearMonth vencimiento, ApiTarjetaCredito apiTCredito)
    {
          super(monto);
          this.gestor = gestor;
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
    public void ejecutarTransaccion(){monto = apiTCredito.ejecutar();}

    @Override
    public void notificarResultado() {

        super.notificarResultado();

        Documento nuevoVoucher = this.generarVoucher();
        gestor.registrarDocumento(nuevoVoucher);
    }

    public Documento generarVoucher() {

         return new VoucherParaPago(codigoTransaccion,nroTarjeta,cvv,monto);
    }

}
