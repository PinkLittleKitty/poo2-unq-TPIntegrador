package org.viajesOnline.reporte;

import org.viajesOnline.Catalogo;

class ServicioMasReservado implements Reporte{

    private Catalogo catalogo;

    public ServicioMasReservado(Catalogo c){

        this.catalogo = c;
    }

    public void accept(FormatoVisitanteReportes v){v.visitar(this);}

    public void calcularDatos(){

        //ret calcula datos no los guarda
        //toma datos de catalogo
    }
}


