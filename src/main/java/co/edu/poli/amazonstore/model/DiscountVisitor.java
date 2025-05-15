package co.edu.poli.amazonstore.model;

public class DiscountVisitor implements Visitor {

    @Override
    public void visitClient(Client client) {
        // No se aplica descuento a los clientes
        System.out.println("No se aplica descuento a los clientes.");

    }

    @Override
    public void visitProduct(Product product) {
        double precioOriginal = product.getPrice();
        double precioConDescuento = precioOriginal * 0.9;
        product.setPrice(precioConDescuento);
    }

    @Override
    public void visitOrder(Order order) {
        for (Product producto : order.getProducts()) {
            producto.accept(this); // Aplica el descuento a cada producto
        }
    }

}
