package org.viajesOnline.reporte;

import org.viajesOnline.catalogo.Item;
import org.viajesOnline.catalogo.Categoria;
import org.viajesOnline.reservas.Cupo;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public final class FormatoUtils {

    public static List<Map.Entry<Item, Long>> serviciosOrdenados(ServicioMasReservado reporte) {
        return reporte.calcularDatos().entrySet().stream()
                .sorted(Map.Entry.<Item, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(e -> e.getKey().getNombre())).toList();
    }

    static List<Map.Entry<Categoria, Cupo>> cuposOrdenados(RegistroDeCupos reporte) {
        return reporte.calcularDatos().entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().name())).toList();
    }

    public static List<Map.Entry<String, Double>> operadoresOrdenados(RegistroDeOperadores reporte) {
        return reporte.calcularDatos().entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey())).toList();
    }

    public static String formatearPorcentaje(double porcentaje) {
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

    public static String escaparCsv(String valor) {
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

    public static String escaparHtml(String valor) {
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

    public static String construirTextoPlano(String titulo, List<String> lineas) {
        StringBuilder sb = new StringBuilder();
        sb.append(titulo);
        if (lineas.isEmpty()) {
            sb.append("\n(sin datos)");
            return sb.toString();
        }

        for (String linea : lineas) {
            sb.append("\n").append(linea);
        }
        return sb.toString();
    }
}