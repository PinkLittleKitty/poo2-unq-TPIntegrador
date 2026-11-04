package org.viajesOnline.reservas;

public class Cupo {
    private int cupoInicial;
    private int cupoDisponible;

    public Cupo( int cupoIni) {
        this.cupoInicial = cupoIni;
        this.cupoDisponible = cupoIni;
    }

    public void decrementarCupo() {
        this.cupoDisponible--;
    }

    public void incrementarCupo() {
        this.cupoDisponible++;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public int getCupoInicial() {
        return cupoInicial;
    }
}
