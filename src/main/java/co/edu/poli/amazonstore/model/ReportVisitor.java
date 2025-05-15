package co.edu.poli.amazonstore.model;

public class ReportVisitor implements Visitor {

    private StringBuilder reporte;

    public ReportVisitor() {
        this.reporte = new StringBuilder();
    }

    @Override
    public void visitClient(Client client) {
        reporte.append("Client Report: ")
                .append(client.getNameClient())
                .append(" - Email: ")
                .append(client.getEmail())
                .append("\n");
    }

    @Override
    public void visitProduct(Product product) {
        reporte.append("Product: ")
                .append(product.getNameProduct())
                .append(" - Price: $")
                .append(product.getPrice())
                .append("\n");
    }

    @Override
    public void visitOrder(Order order) {
        reporte.append("Order ID: ").append(order.getId()).append("\n");
        order.getClient().accept(this); // Cliente también acepta al visitor
        for (Product product : order.getProducts()) {
            product.accept(this);
        }
    }

    public String obtenerReporte() {
        return reporte.toString();
    }

}
