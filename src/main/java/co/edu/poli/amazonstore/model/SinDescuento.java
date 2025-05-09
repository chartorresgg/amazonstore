package co.edu.poli.amazonstore.model;

/**
 * Clase que representa una estrategia concreta de descuento: Sin descuento.
 * Esta clase implementa la interfaz EstrategiaDescuento y define el método
 * calcularDescuento para no aplicar ningún descuento al pedido.
 */
public class SinDescuento implements EstrategiaDescuento {

    /**
     * Método que calcula el descuento a aplicar a un pedido.
     * En este caso, no se aplica ningún descuento.
     * @param pedido El pedido al que se le aplicará el descuento.
     * @return 0.0, ya que no se aplica descuento.
     */
    public double calcularDescuento(Pedido pedido) {
        return 0.0;
    }
    
}