package co.edu.poli.amazonstore.model;

/**
 * Clase Validador COncreto que verifica que todos los productos del pedido tengan stock disponible.
 * Esta clase extiende de ManejadorBase y se encarga de validar el stock de los productos.
 * Si algún producto no tiene stock, lanza una excepción. 
 * Si todos los productos tienen stock, llama al siguiente manejador en la cadena.
 */
public class ValidadorStock extends ManejadorBase {

    /**
     * Método que procesa el pedido y valida si todos los productos tienen stock disponible.
     * Si algún producto no tiene stock, lanza una excepción.
     * Si todos los productos tienen stock, llama al siguiente manejador en la cadena.
     *
     * @param pedido El pedido a validar.
     */
    @Override
    public void procesar(Pedido pedido) {
        boolean hayStock = pedido.getProductos().stream()
            .allMatch(p -> p.getStock() > 0);
        if (!hayStock) {
            throw new RuntimeException("Uno o más productos no tienen stock.");
        }
        System.out.println("Stock validado.");
        super.procesar(pedido);
    }
}
