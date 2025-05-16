package co.edu.poli.amazonstore.model;

public interface OrderState {

    void addProduct(Order order, Product product);
    void avanzarEstado(Order order);
    String obtenerEstado();
    
}