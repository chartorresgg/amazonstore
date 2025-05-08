package co.edu.poli.amazonstore.model;

public class ValidadorClienteActivo extends ManejadorBase {

    @Override
    public void procesar(Pedido pedido) {
        if (!pedido.getCliente().isActivo()) {
            throw new RuntimeException("El cliente no está activo.");
        }
        System.out.println("Cliente activo validado.");
        super.procesar(pedido);
    }
    
}