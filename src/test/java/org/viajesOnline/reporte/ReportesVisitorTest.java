package org.viajesOnline.reporte;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.viajesOnline.items.Categoria;
import org.viajesOnline.items.Paquete;
import org.viajesOnline.items.ServicioIndividual;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;
import org.viajesOnline.utils.Usuario;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportesVisitorTest {
    private Periodo periodoReporte;
    private ServicioIndividual hotelCentral;
    private ServicioIndividual excursionMuseo;
    private ServicioIndividual trasladoAeropuerto;
    private Paquete packEscapadaUrbana;
    private GeneradorDeReportes generador;
    private Catalogo catalogo = Catalogo.getInstance();
    private GestorReservas gestorReservas = GestorReservas.getInstance();

    private Reserva crearReserva(LocalDate fechaCompra) {
        Reserva r = new Reserva(mock(GestorDeDocumentos.class), mock(Usuario.class), periodoReporte);
        r.setFechaCompra(fechaCompra);
        return r;
    }

    @AfterEach
    void reset(){
        catalogo.borrarTodo();
        gestorReservas.borrarTodo();
    }

    @BeforeEach
    void setUp() {
        periodoReporte = new Periodo(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 31));
        Periodo vigenciaEnero = new Periodo(LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 25));

        hotelCentral = new ServicioIndividual(
                "Noche de Hotel Central",
                "Un hotel en el centro",
                new Cupo(10, 6),
                1,
                "Hilton",
                1500.0,
                Categoria.ALOJAMIENTO,
                vigenciaEnero);

        excursionMuseo = new ServicioIndividual(
                "Excursión Guiada Museo",
                "Recorrido guiado",
                new Cupo(15, 10),
                2,
                "Malba",
                2000.0,
                Categoria.EXCURSION,
                vigenciaEnero);

        trasladoAeropuerto = new ServicioIndividual(
                "Traslado Aeropuerto-Hotel",
                "Traslado privado",
                new Cupo(8, 5),
                3,
                "Chofer privado",
                2000.0,
                Categoria.ALQUILER_DE_AUTOS,
                vigenciaEnero);

        packEscapadaUrbana = new Paquete(
                "Pack Escapada Urbana",
                "Hotel + Excursión + Traslado",
                1.0,
                new Cupo(5, 3),
                vigenciaEnero,
                Categoria.EXCURSION,
                "ProveedorA");
        packEscapadaUrbana.agregarItem(hotelCentral);
        packEscapadaUrbana.agregarItem(excursionMuseo);
        packEscapadaUrbana.agregarItem(trasladoAeropuerto);
        packEscapadaUrbana.darDescuento(10);

        Reserva r1 = crearReserva(LocalDate.of(2026, 1, 12));
        r1.getSolicitud().agregarServicio(packEscapadaUrbana);
        r1.getSolicitud().agregarServicio(hotelCentral);

        Reserva r2 = crearReserva(LocalDate.of(2026, 1, 15));
        r2.getSolicitud().agregarServicio(packEscapadaUrbana);
        r2.getSolicitud().agregarServicio(hotelCentral);
        r2.getSolicitud().agregarServicio(excursionMuseo);

        Reserva r3 = crearReserva(LocalDate.of(2026, 1, 20));
        r3.getSolicitud().agregarServicio(packEscapadaUrbana);

        Reserva reservaSinFecha = crearReserva(null);
        reservaSinFecha.getSolicitud().agregarServicio(trasladoAeropuerto);

        Reserva reservaFueraDePeriodo = crearReserva(LocalDate.of(2026, 2, 20));
        reservaFueraDePeriodo.setFechaCompra(LocalDate.of(2026, 2, 10));
        reservaFueraDePeriodo.getSolicitud().agregarServicio(trasladoAeropuerto);

        catalogo.addItem(hotelCentral);
        catalogo.addItem(excursionMuseo);
        catalogo.addItem(trasladoAeropuerto);

        gestorReservas.addReserva(r1);
        gestorReservas.addReserva(r2);
        gestorReservas.addReserva(r3);
        gestorReservas.addReserva(reservaSinFecha);
        gestorReservas.addReserva(reservaFueraDePeriodo);

        generador = new GeneradorDeReportes();
    }

    @Test
    void servicioMasReservadoExporta() {
        Reporte reporte = generador.serviciosMasReservados(periodoReporte);

        assertEquals("Servicio,Reservas\n"
                        + "Pack Escapada Urbana,3\n"
                        + "Noche de Hotel Central,2\n"
                        + "Excursión Guiada Museo,1",
                generador.exportar(reporte, new FormatoCsv()));

        assertEquals(
                "<h2>Servicios mas reservados</h2>\n"
                        + "<table>\n"
                        + "<thead><tr><th>Servicio</th><th>Reservas</th></tr></thead>\n"
                        + "<tbody>\n"
                        + "<tr><td>Pack Escapada Urbana</td><td>3</td></tr>\n"
                        + "<tr><td>Noche de Hotel Central</td><td>2</td></tr>\n"
                        + "<tr><td>Excursión Guiada Museo</td><td>1</td></tr>\n"
                        + "</tbody>\n"
                        + "</table>",
                generador.exportar(reporte, new FormatoHtml()));

        assertEquals(
                "Servicios más reservados\n"
                        + "- Pack Escapada Urbana: 3 reservas\n"
                        + "- Noche de Hotel Central: 2 reservas\n"
                        + "- Excursión Guiada Museo: 1 reservas",
                generador.exportar(reporte, new FormatoTextoPlano()));
    }

    @Test
    void registroDeCuposExporta() {
        Reporte reporte = generador.registroDeCupos(periodoReporte);

        assertEquals(
                "Categoria,Totales,Reservados,Disponibles\n"
                        + "ALOJAMIENTO,10,4,6\n"
                        + "ALQUILER_DE_AUTOS,8,3,5\n"
                        + "EXCURSION,15,5,10",
                generador.exportar(reporte, new FormatoCsv()));

        assertEquals(
                "<h2>Registro de cupos</h2>\n"
                        + "<table>\n"
                        + "<thead><tr><th>Categoria</th><th>Totales</th><th>Reservados</th><th>Disponibles</th></tr></thead>\n"
                        + "<tbody>\n"
                        + "<tr><td>ALOJAMIENTO</td><td>10</td><td>4</td><td>6</td></tr>\n"
                        + "<tr><td>ALQUILER_DE_AUTOS</td><td>8</td><td>3</td><td>5</td></tr>\n"
                        + "<tr><td>EXCURSION</td><td>15</td><td>5</td><td>10</td></tr>\n"
                        + "</tbody>\n"
                        + "</table>",
                generador.exportar(reporte, new FormatoHtml()));
        assertEquals(
                "Registro de cupos\n"
                        + "Categoría: ALOJAMIENTO | Totales: 10 | Reservados: 4 | Disponibles: 6\n"
                        + "Categoría: ALQUILER_DE_AUTOS | Totales: 8 | Reservados: 3 | Disponibles: 5\n"
                        + "Categoría: EXCURSION | Totales: 15 | Reservados: 5 | Disponibles: 10",
                generador.exportar(reporte, new FormatoTextoPlano()));
    }

    @Test
    void registroDeOperadoresExporta() {
        RegistroDeOperadores reporte = generador.registroDeOperadores();
        assertFalse(reporte.hayRiesgoDeConcentracion(60.0));
        assertTrue(reporte.hayRiesgoDeConcentracion(30.0));
        assertEquals(
                "Operador,Porcentaje\n"
                        + "Chofer privado,33.33\n"
                        + "Hilton,33.33\n"
                        + "Malba,33.33",
                generador.exportar(reporte, new FormatoCsv()));
        assertEquals(
                "<h2>Registro de operadores</h2>\n"
                        + "<table>\n"
                        + "<thead><tr><th>Operador</th><th>Porcentaje</th></tr></thead>\n"
                        + "<tbody>\n"
                        + "<tr><td>Chofer privado</td><td>33.33%</td></tr>\n"
                        + "<tr><td>Hilton</td><td>33.33%</td></tr>\n"
                        + "<tr><td>Malba</td><td>33.33%</td></tr>\n"
                        + "</tbody>\n"
                        + "</table>",
                generador.exportar(reporte, new FormatoHtml()));
        assertEquals(
                "Registro de operadores\n"
                        + "- Chofer privado: 33.33%\n"
                        + "- Hilton: 33.33%\n"
                        + "- Malba: 33.33%",
                generador.exportar(reporte, new FormatoTextoPlano()));
    }
}