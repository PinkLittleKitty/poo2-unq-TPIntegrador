package org.viajesOnline.documento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorDeDocumentos{

    private List<Documento> listaDocumentos = new ArrayList<>();

    public void registrarDocumento(Documento unDocumento) {listaDocumentos.add(unDocumento);}

    public List<Documento> obtenerDocumentos() {

        return Collections.unmodifiableList(this.listaDocumentos);
    }

    public int cantidadDocumentos() {return this.listaDocumentos.size();}


}
