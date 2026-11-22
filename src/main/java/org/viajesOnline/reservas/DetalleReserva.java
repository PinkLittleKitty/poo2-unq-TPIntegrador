package org.viajesOnline.reservas;

import org.viajesOnline.items.Item;
import org.viajesOnline.utils.Periodo;

import java.util.ArrayList;
import java.util.List;

public class DetalleReserva {

    private String codTrans;
    private List<Item> servicios = new ArrayList<>();

    public List<Item> getServicios() { return servicios; }
    public int cantServicios() { return servicios.size(); }

    public void agregarServicio(Item s) {
        servicios.add(s);
    }

    public void eliminarServicio(Item s) {
        servicios.remove(s);
    }

    public void decrementarCupo(Periodo periodo) {
        for (Item s: servicios) {
            s.decrementarCupo(periodo);
        }
    }

    public void incrementarCupo(Periodo periodo) {
        for (Item s: servicios) {
            s.incrementarCupo(periodo);
        }
    }

    public double precioTotalServicios() {
        return servicios.stream().mapToDouble(Item::precioFinal).sum();
    }
}
