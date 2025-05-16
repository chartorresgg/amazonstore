package co.edu.poli.amazonstore.model;

public class EstadoEnviado implements OrderState {

    @Override
    public void addProduct(Order order, Product product) {
        System.out.println("No se pueden modificar productos en estado ENVIADO.");
    }

    @Override
    public void avanzarEstado(Order order) {
        order.setState(new EstadoEntregado());
        System.out.println("Pedido cambiado a estado ENTREGADO.");
    }

    @Override
    public String obtenerEstado() {
        return "Enviado";
    }
}