package org.viajesOnline.documento;


public class    VoucherParaFactura extends Documento{

    private String titular;
    private int cantidadDeServicios;
    private double monto;

    public VoucherParaFactura(String codTransaccion, String titular, int cantidadDeServicios, double monto) {
        super(codTransaccion);
        this.titular = titular;
        this.cantidadDeServicios = cantidadDeServicios;
        this.monto = monto;
    }

    public String getTitular() { return titular; }
    public int getCantidadDeServicios() { return cantidadDeServicios; }
    public double getMonto() { return monto; }

    @Override
    public void mostrarDetalles() {
        super.mostrarDetalles();
        System.out.println("VOUCHER DE VIAJE");
        System.out.println("Titular: " + this.getTitular());
        System.out.println("Servicios contratados: " + this.getCantidadDeServicios());
        System.out.println("Total Pagado: $" + this.getMonto());
    }


}
