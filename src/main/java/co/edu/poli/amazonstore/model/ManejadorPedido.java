package co.edu.poli.amazonstore.model;

/**
 * Clase Interfaz del patrón Chain of Responsibility que define los métodos para procesar
 * pedidos en cadena.
 */
public interface ManejadorPedido {

    /**
     * Método para establecer el siguiente manejador en la cadena de responsabilidad.
     * @param siguiente El siguiente manejador a establecer.
     */
    void setSiguiente(ManejadorPedido siguiente);

    /**
     * Método para procesar un pedido.
     * @param pedido El pedido a procesar.
     */
    void procesar(Pedido pedido);
    
}