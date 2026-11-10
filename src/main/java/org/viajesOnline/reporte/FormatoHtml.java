package org.viajesOnline.reporte;

import org.viajesOnline.ServicioIndividual;

import java.util.Map;

class FormatoHtml implements FormatoVisitanteReportes {

    private String reporteHtml;

    public void visitar(ServicioMasReservado s){

        //guarda la informacion para exportar en formato html
        //reporteHtml = e.pideCosasALaClase
    }

    public void visitar(RegistroDeCupos r){
        /*
        var r = registrocupos.calcularDatos();

        for (Map.entry<Categoria, Cupo> entrada : reporteCupos.entrySet()) {
            Categoria cat = entrada.getKey();
            Cupo cupo = entrada.getValue();

            int totales = cupo.getCupoInicial();
            libres;
            reservados;

            System.out.println("Categoria: " + categoria);
            System.out.println(" - Totales: " + totales);
            System.out.println(" - Reservados: " + reservados);
            System.out.println(" - Libres: " + libres);
        } */
    }

    public void visitar(RegistroDeOperadores o){

        //guarda la informacion para exportar en formato html
        //reporteHtml = e.pideCosasALaClase
    }

    public String obtenerReporte(){

        //retorna el reporte en html
        return reporteHtml;
    }
}

