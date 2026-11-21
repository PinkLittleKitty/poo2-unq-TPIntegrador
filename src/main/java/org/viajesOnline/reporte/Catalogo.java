package org.viajesOnline.reporte;

import org.viajesOnline.criterios.Criterio;
import org.viajesOnline.items.*;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private final List<Item> items = new ArrayList<>();
//    private final List<Reserva> reservas = new ArrayList<>();
    private static Catalogo instance;

    private Catalogo() {} // Private constructor

    public static Catalogo getInstance() {
        if (instance == null) {
            instance = new Catalogo();
        }
            return instance;
    }

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

    void borrarTodo(){
        items.clear();
    }
}
