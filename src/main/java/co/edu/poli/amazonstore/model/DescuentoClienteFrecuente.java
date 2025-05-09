package co.edu.poli.amazonstore.model;

/**
 * Clase que representa la estrategia concreta de descuento para clientes frecuentes.
 * Esta estrategia aplica un 10% de descuento sobre el total bruto del pedido.
 * Esta clase implementa la interfaz EstrategiaDescuento y define el método
 * calcularDescuento para aplicar el descuento correspondiente.
 */
public class DescuentoClienteFrecuente implements EstrategiaDescuento {

    /**
     * Método que calcula el descuento a aplicar a un pedido.
     * En este caso, se aplica un 10% de descuento sobre el total bruto del pedido.
     * @param pedido El pedido al que se le aplicará el descuento.
     * @return El monto del descuento aplicado.
     */
    public double calcularDescuento(Pedido pedido) {
        return pedido.calcularTotalBruto() * 0.10; // 10% descuento
    }
    
}