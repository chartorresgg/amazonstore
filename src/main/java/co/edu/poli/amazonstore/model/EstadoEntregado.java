package co.edu.poli.amazonstore.model;

public class EstadoEntregado implements OrderState {

    @Override
    public void addProduct(Order order, Product product) {
        System.out.println("El pedido ya fue entregado. No se puede modificar.");
    }

    @Override
    public void avanzarEstado(Order order) {
        System.out.println("El pedido ya está ENTREGADO. No puede avanzar.");
    }

    @Override
    public String obtenerEstado() {
        return "Entregado";
    }
}