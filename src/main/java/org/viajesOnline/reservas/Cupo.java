package org.viajesOnline.reservas;

public class Cupo {
    private int cupoInicial;
    private int cupoDisponible;

    public Cupo(int cupoIni, int cupoDisponible) {
        this.cupoInicial = cupoIni;
        this.cupoDisponible = cupoDisponible;
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

    public int getCantCupoReservados() { return cupoInicial - cupoDisponible; }
}
