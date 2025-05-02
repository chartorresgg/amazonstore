package co.edu.poli.amazonstore.model;

public class ProductoSuscriptor implements Subscriber {

    private final Producto producto;

    public ProductoSuscriptor(Producto producto) {
        this.producto = producto;
    }

    @Override
    public void actualizarPrecio(double porcentaje) {
        double nuevoPrecio = producto.getPrecioActual() * (1 + porcentaje / 100);
        producto.setPrecioActual(nuevoPrecio);
    }

    public Producto getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return producto.toString();
    }
}
