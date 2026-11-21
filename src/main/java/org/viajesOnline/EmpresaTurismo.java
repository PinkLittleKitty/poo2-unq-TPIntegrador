package org.viajesOnline;

import org.viajesOnline.criterios.Criterio;
import org.viajesOnline.items.*;
import org.viajesOnline.reservas.Reserva;

import java.util.ArrayList;
import java.util.List;

public class EmpresaTurismo {
    private static final List<Item> items = new ArrayList<>();
    private static final List<Reserva> reservas = new ArrayList<>();

    public static void agregarItem(Item i) {
        items.add(i);
    }

    public static void sacarItem(Item i) {
        items.remove(i);
    }

    public static List<Item> getItems() {
        return List.copyOf(items);
    }

    public List<Item> buscarEnCatalogo(Criterio criterio){
        return items.stream().filter(criterio::satisfaceCriterio).toList();
    }
}
