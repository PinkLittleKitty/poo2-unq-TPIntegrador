package org.viajesOnline.reporte;

import org.viajesOnline.Catalogo;
import org.viajesOnline.Categoria;
import org.viajesOnline.reservas.Cupo;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.*;

final class FormatoUtils {
    private FormatoUtils() {}

    static List<Map.Entry<Catalogo, Long>> serviciosOrdenados(ServicioMasReservado reporte) {
        return reporte.calcularDatos().entrySet().stream()
                .sorted(Map.Entry.<Catalogo, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(e -> e.getKey().getNombre())).toList();
    }

    static List<Map.Entry<Categoria, Cupo>> cuposOrdenados(RegistroDeCupos reporte) {
        return reporte.calcularDatos().entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().name())).toList();
    }

    static List<Map.Entry<String, Double>> operadoresOrdenados(RegistroDeOperadores reporte) {
        return reporte.calcularDatos().entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey())).toList();
    }

    static String formatearPorcentaje(double porcentaje) {
        return String.format(Locale.US, "%.2f", porcentaje);
    }

    static String construirCsv(List<String> encabezados, List<List<String>> filas) {
        StringBuilder sb = new StringBuilder();
        sb.append(lineaCsv(encabezados));
        for (List<String> fila : filas) {
            sb.append("\n").append(lineaCsv(fila));
        }
        return sb.toString();
    }

    private static String lineaCsv(List<String> campos) {
        return campos.stream()
                .map(FormatoUtils::escaparCsv)
                .collect(Collectors.joining(","));
    }

    static String escaparCsv(String valor) {
        if (valor == null) {
            return "";
        }

        if (valor.contains(",") || valor.contains("\"") || valor.contains("\n") || valor.contains("\r")) {
            return "\"" + valor.replace("\"", "\"\"") + "\"";
        }

        return valor;
    }

    static String construirTablaHtml(String titulo, List<String> encabezados, List<List<String>> filas) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>").append(escaparHtml(titulo)).append("</h2>\n");
        sb.append("<table>\n<thead><tr>");
        for (String encabezado : encabezados) {
            sb.append("<th>").append(escaparHtml(encabezado)).append("</th>");
        }
        sb.append("</tr></thead>\n<tbody>\n");
        for (List<String> fila : filas) {
            sb.append("<tr>");
            for (String celda : fila) {
                sb.append("<td>").append(escaparHtml(celda)).append("</td>");
            }
            sb.append("</tr>\n");
        }
        sb.append("</tbody>\n</table>");
        return sb.toString();
    }

    static String escaparHtml(String valor) {
        if (valor == null) {
            return "";
        }

        return valor
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}