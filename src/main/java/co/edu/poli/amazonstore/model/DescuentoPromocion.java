package co.edu.poli.amazonstore.model;

/**
 * Clase que representa una estrategia concreta que aplica un 20% de descuento de promoción.
 * Esta clase implementa la interfaz EstrategiaDescuento y define el método
 * calcularDescuento para aplicar el descuento correspondiente.
 */
public class DescuentoPromocion implements EstrategiaDescuento {

    /**
     * Método que calcula el descuento a aplicar a un pedido.
     * En este caso, se aplica un 20% de descuento sobre el total bruto del pedido.
     * @param pedido El pedido al que se le aplicará el descuento.
     * @return El monto del descuento aplicado.
     */
    public double calcularDescuento(Pedido pedido) {
        return pedido.calcularTotalBruto() * 0.20; // 20% descuento
    }
    
}