package org.viajesOnline.documento;

import java.util.ArrayList;
import java.util.List;

public class GestorDeDocumentos{

    private List<Documento> listaComprobantes = new ArrayList<>();
    private List<Documento>listaDeVouchers = new ArrayList<>();

    public void registrarVoucher(Documento voucher) {listaDeVouchers.add(voucher);}

    public void registrarComprobanteCbu(Documento comprobanteCbu) {listaComprobantes.add(comprobanteCbu);}
}
