package org.viajesOnline.reservas;

import org.viajesOnline.Catalogo;
import org.viajesOnline.documento.Documento;
import org.viajesOnline.documento.VoucherReembolso;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Solicitud {

    private String codTrans;
    private List<Catalogo> servicios = new ArrayList<>();

    public List<Catalogo> getServicios() { return servicios; }

    public void agregarServicio(Catalogo s) {
        servicios.add(s);
    }

    public void eliminarServicio(Catalogo s) {
        servicios.remove(s);
    }

    public void decrementarCupo() {
        for (Catalogo s: servicios) {
            s.decrementarCupo();
        }
    }

    public void incrementarCupo() {
        for (Catalogo s: servicios) {
            s.incrementarCupo();
        }
    }

    public void reembolsar() {

        codTrans = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        //TODO: Falta agregar los datos propios de un voucher de reembolso servicio y tasas
        Documento reembolso = new VoucherReembolso(codTrans,1,1);
    }
}
