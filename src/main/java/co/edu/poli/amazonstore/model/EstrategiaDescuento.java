package co.edu.poli.amazonstore.model;

/**
 * Interfaz del Patrón Strategy que define el método para calcular descuentos sobre un pedido.
 * Esta interfaz permite implementar diferentes estrategias de descuento que pueden ser aplicadas
 * a los pedidos en la tienda.
 */
public interface EstrategiaDescuento {

    /**
     * Método para calcular el descuento a aplicar a un pedido.
     * @param pedido El pedido al que se le aplicará el descuento.
     */
    double calcularDescuento(Pedido pedido);
    
}