package co.edu.poli.amazonstore.model;

public class Producto {
    
    private String nombreProducto;
    private double precio;

    public Producto(String nombreProducto, double precio) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    
}