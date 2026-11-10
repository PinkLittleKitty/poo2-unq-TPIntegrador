package org.viajesOnline.documento;

public class ComprobanteFiscal extends Documento {

    private String tipoFactura;
    private String nombreCliente;
    private String identificacionFiscal;
    private double importeNeto;
    private double iva;
    private double montoTotal;

    public ComprobanteFiscal(String codTransaccion, String tipoFactura, String nombreCliente, String identificacionFiscal, double montoTotal)
    {
        super(codTransaccion);
        this.tipoFactura = tipoFactura;
        this.nombreCliente = nombreCliente;
        this.identificacionFiscal = identificacionFiscal;
        this.montoTotal = montoTotal;
        this.importeNeto = this.calcularIva(montoTotal / 1.21);
        this.iva = this.calcularIva(montoTotal - this.importeNeto);
    }

    public String getTipoFactura() { return tipoFactura; }
    public String getNombreCliente() { return nombreCliente; }
    public String getIdentificacionFiscal() { return identificacionFiscal; }
    public double getImporteNeto() { return importeNeto; }
    public double getIva() { return iva;}
    public double monto() { return montoTotal; }

    public double calcularIva(double montoInicial) {
        return Math.round(montoInicial * 100.0) / 100.0;
    }

    @Override
    public void mostrarDetalles() {
        super.mostrarDetalles();
        System.out.println("FACTURA TIPO: " + tipoFactura);
        System.out.println("Cliente: " + nombreCliente + " (" + identificacionFiscal + ")");
        System.out.println("Subtotal Neto: $" + importeNeto);
        System.out.println("IVA (21%): $" + iva);
        System.out.println("Total: $" + montoTotal);
    }
}


