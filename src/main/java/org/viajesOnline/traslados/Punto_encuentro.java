package org.viajesOnline.traslados;

public class Punto_encuentro implements CostoTraslado {

    private boolean hayGuiasDisponibles = true;

    public void guiasDisponibles(boolean guiasDisponibles) {
        this.hayGuiasDisponibles = guiasDisponibles;
    }

    @Override
    public float calcularCosto(ContextoTraslado c){
        this.hayGuiasDisponibles();
        return 0;
    }

    private void hayGuiasDisponibles() {
        if (!hayGuiasDisponibles) {
            throw new UnsupportedOperationException("No hay guías disponibles");
        }
    }
}