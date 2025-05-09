package co.edu.poli.amazonstore.model;

/**
 * Clase que representa un Producto en la tienda.
 * Esta clase contiene información sobre el nombre del producto, su precio y su stock disponible.
 */
public class Producto {
    
    private String nombreProducto;
    private double precio;
    private int stock;

    /**
     * Método constructor de la clase Producto
     * @param nombreProducto
     * @param precio
     * @param stock
     */
    public Producto(String nombreProducto, double precio, int stock) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
    }


    //========Getters y Setters========//
    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}