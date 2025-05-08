package co.edu.poli.amazonstore.model;

public class SinDescuento implements EstrategiaDescuento {

    public double calcularDescuento(Pedido pedido) {
        return 0.0;
    }
    
}