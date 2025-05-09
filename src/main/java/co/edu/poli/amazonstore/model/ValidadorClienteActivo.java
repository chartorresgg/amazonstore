package co.edu.poli.amazonstore.model;

/**
 * Clase que representa un Validador Concreto que verifica si el cliente del pedido está activo.
 * Esta clase extiende de ManejadorBase y se encarga de validar el estado del cliente.
 */
public class ValidadorClienteActivo extends ManejadorBase {

    /**
     * Método que procesa el pedido y valida si el cliente está activo.
     * Si el cliente no está activo, lanza una excepción.
     * Si el cliente está activo, llama al siguiente manejador en la cadena.
     */
    @Override
    public void procesar(Pedido pedido) {
        if (!pedido.getCliente().isActivo()) {
            throw new RuntimeException("El cliente no está activo.");
        }
        System.out.println("Cliente activo validado.");
        super.procesar(pedido);
    }
    
}