package co.edu.poli.amazonstore.model;

/**
 * Visitor que calcula el total de impuestos (IVA 19%) sobre los productos del pedido.
 */
public class TaxVisitor implements Visitor {

    private double totalTax = 0.0;

    @Override
    public void visitClient(Client client) {
        // No aplica impuestos al cliente
    }

    @Override
public void visitProduct(Product product) {
    double tax = product.getPrice() * 0.19;
    product.setTaxAmount(tax);
    totalTax += tax;  // 👈 acumula el impuesto correctamente
}



    @Override
    public void visitOrder(Order order) {
        for (Product product : order.getProducts()) {
            product.accept(this);  // Calcula el impuesto para cada producto
        }
    }

    public double getTotalTax() {
        return Math.round(totalTax * 100.0) / 100.0;
    }
}