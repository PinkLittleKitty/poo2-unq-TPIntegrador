package org.viajesOnline.reporte;

import org.viajesOnline.Catalogo;

class RegistroDeOperadores {

    private Catalogo catalogo;

    public RegistroDeOperadores(Catalogo c){

        this.catalogo = c;
    }

    public void accept(FormatoVisitante v){v.visitar(this);}

    public void calcularDatos(){

        //ret calcula datos no los guarda
        //toma datos de catalogo
    }
}

