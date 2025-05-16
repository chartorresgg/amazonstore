package co.edu.poli.amazonstore.model;

public class EstadoNuevo implements OrderState {

    @Override
    public void addProduct(Order order, Product product) {
        order.getProducts().add(product);
        System.out.println("Producto agregado en estado NUEVO.");
    }

    @Override
    public void avanzarEstado(Order order) {
        order.setState(new EstadoConfirmado());
        System.out.println("Pedido cambiado a estado CONFIRMADO.");
    }

    @Override
    public String obtenerEstado() {
        return "Nuevo";
    }
}