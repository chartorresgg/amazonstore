package co.edu.poli.amazonstore.model;

public class ValidadorStock extends ManejadorBase {
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
