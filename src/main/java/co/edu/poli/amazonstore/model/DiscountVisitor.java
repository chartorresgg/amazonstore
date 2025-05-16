package co.edu.poli.amazonstore.model;

/**
 * Visitor que aplica un descuento del 10% a todos los productos.
 * No aplica descuento a los clientes.
 * Este visitor implementa el patrón Visitor.
 */
public class DiscountVisitor implements Visitor {

    /**
     * Visita un cliente.
     * No se aplica descuento a los clientes.
     */
    @Override
    public void visitClient(Client client) {
        // No se aplica descuento a los clientes
    }

    /**
     * Aplica un descuento del 10% al precio del producto.
     * El precio se redondea a dos decimales.
     *
     * @param product El producto al que se le aplica el descuento.
     */
    @Override
    public void visitProduct(Product product) {
        double precioOriginal = product.getPrice();
        double precioConDescuento = Math.round(precioOriginal * 0.9 * 100.0) / 100.0;
    product.setPrice(precioConDescuento);
    }

    /**
     * Visita un pedido y aplica el descuento a todos los productos en el pedido.
     *
     * @param order El pedido que contiene los productos.
     */
    @Override
    public void visitOrder(Order order) {
        for (Product producto : order.getProducts()) {
            producto.accept(this); // Aplica el descuento a cada producto
        }
    }

}
