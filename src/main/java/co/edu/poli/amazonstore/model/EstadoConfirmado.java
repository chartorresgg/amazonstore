package co.edu.poli.amazonstore.model;

public class EstadoConfirmado implements OrderState {

    @Override
    public void addProduct(Order order, Product product) {
        System.out.println("No se pueden agregar productos en estado CONFIRMADO.");
    }

    @Override
    public void avanzarEstado(Order order) {
        order.setState(new EstadoEnviado());
        System.out.println("Pedido cambiado a estado ENVIADO.");
    }

    @Override
    public String obtenerEstado() {
        return "Confirmado";
    }
}