package co.edu.poli.amazonstore.model;

public class DescuentoClienteFrecuente implements EstrategiaDescuento {

    public double calcularDescuento(Pedido pedido) {
        return pedido.getTotalBruto() * 0.10; // 10% descuento
    }
    
}