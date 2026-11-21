package org.viajesOnline;

import org.viajesOnline.criterios.Criterio;
import org.viajesOnline.items.*;
import org.viajesOnline.reservas.Reserva;

import java.util.ArrayList;
import java.util.List;

public class EmpresaTurismo {
    private final List<Item> items = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();

    public void addItem(Item i) {
        items.add(i);
    }

    public void delItem(Item i) {
        items.remove(i);
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }

    public List<Item> buscarEnCatalogo(Criterio criterio){
        return items.stream().filter(criterio::satisfaceCriterio).toList();
    }
}
