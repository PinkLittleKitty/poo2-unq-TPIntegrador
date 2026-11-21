package org.viajesOnline.reservas;

import org.viajesOnline.catalogo.Item;

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

    public void decrementarCupo() {
        for (Item s: servicios) {
            s.decrementarCupo();
        }
    }

    public void incrementarCupo() {
        for (Item s: servicios) {
            s.incrementarCupo();
        }
    }

    public double precioTotalServicios() {
        return servicios.stream().mapToDouble(Item::precioFinal).sum();
    }
}
