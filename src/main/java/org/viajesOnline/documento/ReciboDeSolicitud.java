package org.viajesOnline.documento;

public class ReciboDeSolicitud extends Documento{

    private String estadoDeSolicitud;
    private String emailDestinatario;
    private double montoTotal;
    private int cantidadItems;
    private String nombreRecibo;

    public ReciboDeSolicitud(String codigoTransaccion, String estadoDeSolicitud
                             ,String emailDestinatario, double montoTotal, int cantidadItems) {
        super(codigoTransaccion);
        this.estadoDeSolicitud = estadoDeSolicitud;
        this.emailDestinatario = emailDestinatario;
        this.montoTotal = montoTotal;
        this.cantidadItems = cantidadItems;
        this.nombreRecibo = "Recibo Solicitud";
    }

    public String tipoDocumento() { return nombreRecibo; }
    public String getEstadoDeSolicitud() { return estadoDeSolicitud; }
    public String getEmailDestinatario() { return emailDestinatario; }
    public double getMontoTotal() { return montoTotal; }
    public int getCantidadItems() { return cantidadItems; }

    @Override
    public void mostrarDetalles() {
        super.mostrarDetalles();
        System.out.println("RECIBO DE SOLICITUD");
        System.out.println("Destinatario: " + this.getEmailDestinatario());
        System.out.println("Estado actual: " + this.getEstadoDeSolicitud());
        System.out.println("Cantidad de items: " + this.getCantidadItems());
        System.out.println("Monto Total: $" + this.getMontoTotal());
    }
}
