package org.viajesOnline.reservas;

import org.viajesOnline.Catalogo;

import java.util.List;

public class Solicitud {
    private List<Catalogo> servicios;

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

    public void reiniciarCupo() {
        for (Catalogo s: servicios) {
            s.incrementarCupo();
        }
    }
}
