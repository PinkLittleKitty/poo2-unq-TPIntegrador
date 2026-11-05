package org.viajesOnline.gestorDeRecibos;


import java.util.ArrayList;
import java.util.List;


public class GestorDeRecibos {

    // Cada concepto en su propia lista con su tipo correspondiente
    private List<VoucherParaFactura> historialVouchersFactura = new ArrayList<>();
    private List<VoucherParaPago> historialVouchersParaPagos = new ArrayList<>();
    //private List<VoucherReembolso> historialVoucchersReembolso = new ArrayList<>();
    //private List<ComprobanteFiscal> historialComprobanteFiscal = new ArrayList<>();
    //private List<ComprobanteCbu> historialComprobanteCbu = new ArrayList<>();

    public void registrarVoucher(VoucherParaFactura vouchersFactura) {

        historialVouchersFactura.add(vouchersFactura);
    }

    public void registrarVoucherParaPago(VoucherParaPago voucherPago) {

        historialVouchersParaPagos.add(voucherPago);
    }

    /*
    public void registrarReserva(VoucherReembolso voucherReembolso) {

        historialVoucchersReembolso.add(voucherReembolso);
    }

    public void registrarComprobanteFiscal(ComprobanteFiscal comprobanteFiscal) {

        historialComprobanteFiscal.add(comprobanteFiscal);
    }

    public void registrarComprobanteCbu(ComprobanteCbu comprobanteCbu) {

        historialComprobanteCbu.add(comprobanteCbu);
    }*/

    public List<VoucherParaPago> obtenerVouchersParaPago(){return historialVouchersParaPagos;}
}
