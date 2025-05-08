package co.edu.poli.amazonstore.model;

public class ValidadorMontoMinimo extends ManejadorBase {
    

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
