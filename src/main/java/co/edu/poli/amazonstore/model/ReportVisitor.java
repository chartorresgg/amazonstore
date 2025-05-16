package co.edu.poli.amazonstore.model;

/**
 * Visitor que genera un reporte con la información del pedido.
 * Este visitor implementa el patrón Visitor.
 * Genera un reporte que incluye información del cliente y los productos.
 */
public class ReportVisitor implements Visitor {

    private StringBuilder report;

    public ReportVisitor() {
        this.report = new StringBuilder();
    }

    /**
     * Visita un cliente y agrega su información al reporte.
     *
     * @param client El cliente que se va a agregar al reporte.
     */
    @Override
    public void visitClient(Client client) {
        report.append("Client Report: ")
                .append(client.getNameClient())
                .append(" - Email: ")
                .append(client.getEmail())
                .append("\n");
    }

    /**
     * Visita un producto y agrega su información al reporte.
     *
     * @param product El producto que se va a agregar al reporte.
     */
    @Override
    public void visitProduct(Product product) {
        report.append("Product: ")
                .append(product.getNameProduct())
                .append(" - Price: $")
                .append(product.getPrice())
                .append("\n");
    }

    /**
     * Visita un pedido y genera un reporte con la información del cliente y los productos.
     *
     * @param order El pedido que contiene los productos.
     */
    @Override
    public void visitOrder(Order order) {
        report.append("Order ID: ").append(order.getId()).append("\n");
        order.getClient().accept(this); // Cliente también acepta al visitor
        for (Product product : order.getProducts()) {
            product.accept(this);
        }
    }

    /**
     * Método para obtener el reporte generado.
     * @return
     */
    public String obtenerReporte() {
        return report.toString().trim() + "\n";
    }

}