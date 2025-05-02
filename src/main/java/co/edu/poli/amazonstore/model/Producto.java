package co.edu.poli.amazonstore.model;

public class Producto {

    private final String nombreProducto;
    private final Proveedor proveedor;
    private double precioActual;

    public Producto(String nombreProducto, Proveedor proveedor, double precioInicial) {
        this.nombreProducto = nombreProducto;
        this.proveedor = proveedor;
        this.precioActual = precioInicial;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    @Override
    public String toString() {
        return nombreProducto + " (" + proveedor.getNombre() + ") - $" + precioActual;
    }
}
