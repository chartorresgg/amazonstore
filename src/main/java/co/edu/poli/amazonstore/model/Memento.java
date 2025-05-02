package co.edu.poli.amazonstore.model;

public class Memento {
    private final double precio;
    private final int año;

    public Memento(double precio, int año) {
        this.precio = precio;
        this.año = año;
    }

    public double getPrecio() {
        return precio;
    }

    public int getAño() {
        return año;
    }
}
