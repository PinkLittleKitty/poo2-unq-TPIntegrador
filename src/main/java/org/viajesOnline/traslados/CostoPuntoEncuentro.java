package org.viajesOnline.traslados;

public class CostoPuntoEncuentro implements Traslado {

    private boolean hayGuiasDisponibles = true;

    public void guiasDisponibles(boolean guiasDisponibles) {
        this.hayGuiasDisponibles = guiasDisponibles;
    }

    @Override
    public float calcularCosto(float precio){
        this.hayGuiasDisponibles();
        return 0;
    }

    private void hayGuiasDisponibles() {
        if (!hayGuiasDisponibles) {
            throw new UnsupportedOperationException("No hay guías disponibles");
        }
    }
}