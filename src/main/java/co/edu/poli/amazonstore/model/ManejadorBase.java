package co.edu.poli.amazonstore.model;

/**
 * Clase abstracta base que implementa el encadenamiento entre los validadores del pedido.
 * Esta clase implementa la interfaz ManejadorPedido y proporciona la funcionalidad
 * común para establecer el siguiente manejador en la cadena y procesar el pedido.
 */
public abstract class ManejadorBase implements ManejadorPedido {

    protected ManejadorPedido siguiente; // Siguiente manejador en la cadena.

    /**
     * Método para establecer el siguiente manejador en la cadena de responsabilidad.
     * @param siguiente El siguiente manejador a establecer.
     */
    @Override
    public void setSiguiente(ManejadorPedido siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Método para procesar un pedido.
     * Este método llama al siguiente manejador en la cadena, si existe.
     * @param pedido El pedido a procesar.
     */
    @Override
    public void procesar(Pedido pedido) {
        if (siguiente != null) {
            siguiente.procesar(pedido);
        }
    }
}