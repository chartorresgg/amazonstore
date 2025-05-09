package co.edu.poli.amazonstore.model;

/**
 * Clase Validador Concreto que verifica si el total del pedido cumple con el monto mínimo requerido.
 * Esta clase extiende de ManejadorBase y se encarga de validar el monto mínimo del pedido.
 * Si el total del pedido es menor al monto mínimo, lanza una excepción.
 */
public class ValidadorMontoMinimo extends ManejadorBase {
    
    /**
     * Método que procesa el pedido y valida si el total cumple con el monto mínimo requerido.
     * Si el total es menor al monto mínimo, lanza una excepción.
     * Si el total es mayor o igual al monto mínimo, llama al siguiente manejador en la cadena.
     *
     * @param pedido El pedido a validar.
     */
   @Override
    public void procesar(Pedido pedido) {
        double total = pedido.getProductos().stream()
            .mapToDouble(Producto::getPrecio)
            .sum();

        if (total < pedido.getMontoMinimo()) {
            throw new RuntimeException("El pedido no alcanza el monto mínimo requerido.");
        }

        System.out.println("Monto mínimo validado.");
        super.procesar(pedido);
    }
}