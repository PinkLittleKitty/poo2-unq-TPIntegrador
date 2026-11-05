package org.viajesOnline.metodoPago;
import org.viajesOnline.exceptions.MetodoPagoExcepcion;
import org.viajesOnline.gestorDeRecibos.GestorDeRecibos;
import org.viajesOnline.gestorDeRecibos.Voucher;
import org.viajesOnline.gestorDeRecibos.VoucherParaPago;


import java.time.YearMonth;

public class MetodoPagoTarjetaInternacional extends MetodoPago {

    private String nroTarjeta;
    private String cvv;
    private YearMonth vencimiento;
    private ApiTarjetaCredito apiTCredito;

    public MetodoPagoTarjetaInternacional(GestorDeRecibos gestor,String nroTarjeta, String cvv
                                        , YearMonth vencimiento, ApiTarjetaCredito apiTCredito)
    {
          super(gestor);
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
        VoucherParaPago nuevoVoucher = this.generarVoucher();
        gestor.registrarVoucherParaPago(nuevoVoucher);
    }

    public VoucherParaPago generarVoucher() {

         return new VoucherParaPago(ultimoCodigoTransaccion,nroTarjeta,cvv,monto);
    }

}
