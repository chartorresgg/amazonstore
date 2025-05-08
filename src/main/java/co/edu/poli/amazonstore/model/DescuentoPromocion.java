package co.edu.poli.amazonstore.model;

public class DescuentoPromocion implements EstrategiaDescuento {

    public double calcularDescuento(Pedido pedido) {
        return pedido.calcularTotalBruto() * 0.20; // 20% descuento
    }
    
}