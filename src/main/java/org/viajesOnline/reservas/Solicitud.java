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
}
