package org.viajesOnline.reservas;

import org.viajesOnline.items.Item;
import org.viajesOnline.utils.Periodo;

import java.util.ArrayList;
import java.util.List;

public class DetalleReserva {

    private String codTrans;

    /// TO DO AL SER UNA LISTA DE SERVICIOS Y PAQUETES NO DEBERIA DE SER ITEMS EL NOMBRE?
    private List<Item> items = new ArrayList<>();

    public List<Item> getServicios() { return items; }
    public int cantServicios() { return items.size(); }

    public void agregarServicio(Item s) {
        items.add(s);
    }

    public void eliminarServicio(Item s) {
        items.remove(s);
    }

    public void decrementarCupo(Periodo periodo) {
        for (Item s: items) {
            s.decrementarCupo(periodo);
        }
    }

    public void incrementarCupo(Periodo periodo) {
        for (Item s: items) {
            s.incrementarCupo(periodo);
        }
    }

    public double precioTotalServicios() {
        return items.stream().mapToDouble(Item::precioFinal).sum();
    }
}
