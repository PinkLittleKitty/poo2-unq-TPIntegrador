package org.viajesOnline.reporte;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.items.*;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.*;
import org.viajesOnline.utils.Periodo;
import org.viajesOnline.utils.Usuario;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FormatoUtilsTest {
    private Catalogo catalogo = Catalogo.getInstance();
    private GestorReservas gestor = GestorReservas.getInstance();

    @AfterEach
    void reset(){
        catalogo.borrarTodo();
        gestor.borrarTodo();
    }

    @Test
    void escaparCsv() {
        assertEquals("", FormatoUtils.escaparCsv(null));
        assertEquals("normal", FormatoUtils.escaparCsv("normal"));
        assertEquals("\"con,coma\"", FormatoUtils.escaparCsv("con,coma"));
        assertEquals("\"con\"\"comillas\"\"\"", FormatoUtils.escaparCsv("con\"comillas\""));
        assertEquals("\"linea1\nlinea2\"", FormatoUtils.escaparCsv("linea1\nlinea2"));
        assertEquals("\"linea1\rlinea2\"", FormatoUtils.escaparCsv("linea1\rlinea2"));
    }

    @Test
    void escaparHtml() {
        assertEquals("", FormatoUtils.escaparHtml(null));
        assertEquals("normal", FormatoUtils.escaparHtml("normal"));
        assertEquals("&amp;&lt;&gt;&quot;&#39;", FormatoUtils.escaparHtml("&<>\"'"));
    }

    @Test
    void construirTextoPlanoSinDatos() {
        String resultado = FormatoUtils.construirTextoPlano("Titulo Reporte", List.of());
        assertEquals("Titulo Reporte\n(sin datos)", resultado);
    }

    @Test
    void formatearPorcentaje() {
        assertEquals("45.67", FormatoUtils.formatearPorcentaje(45.67));
        assertEquals("10.00", FormatoUtils.formatearPorcentaje(10.0));
    }

    @Test
    void serviciosOrdenadosDesempatePorNombre() {
        Periodo periodo = new Periodo(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 31));
        Periodo vigencia = new Periodo(LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20));

        ServicioIndividual sB = new ServicioIndividual("Servicio B", "Desc", 10, 1, "Op", 100.0, Categoria.VUELO, vigencia);
        ServicioIndividual sA = new ServicioIndividual("Servicio A", "Desc", 10, 2, "Op", 100.0, Categoria.VUELO, vigencia);

        Reserva r1 = new Reserva(mock(GestorDeDocumentos.class), mock(Usuario.class), periodo);
        r1.setFechaCompra(LocalDate.of(2026, 1, 15));
        r1.getSolicitud().agregarServicio(sB);

        Reserva r2 = new Reserva(mock(GestorDeDocumentos.class), mock(Usuario.class), periodo);
        r2.setFechaCompra(LocalDate.of(2026, 1, 15));
        r2.getSolicitud().agregarServicio(sA);

        gestor.addReserva(r1);
        gestor.addReserva(r2);

        ServicioMasReservado reporte = new ServicioMasReservado(periodo);

        var lista = FormatoUtils.serviciosOrdenados(reporte);
        assertEquals(2, lista.size());
        assertEquals("Servicio A", lista.get(0).getKey().getNombre());
        assertEquals("Servicio B", lista.get(1).getKey().getNombre());
    }

    @Test
    void operadoresOrdenadosDesempatePorNombre() {
        Periodo vigencia = new Periodo(LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20));

        ServicioIndividual s1 = new ServicioIndividual("S1", "Desc", 10, 1, "Operador Z", 100.0, Categoria.VUELO, vigencia);
        ServicioIndividual s2 = new ServicioIndividual("S2", "Desc", 10, 2, "Operador A", 100.0, Categoria.VUELO, vigencia);

        catalogo.addItem(s1);
        catalogo.addItem(s2);
        RegistroDeOperadores reporte = new RegistroDeOperadores();

        var lista = FormatoUtils.operadoresOrdenados(reporte);
        assertEquals(2, lista.size());
        assertEquals("Operador A", lista.get(0).getKey());
        assertEquals("Operador Z", lista.get(1).getKey());
    }

    @Test
    void instanciacion() {
        assertNotNull(new FormatoUtils());
    }
}