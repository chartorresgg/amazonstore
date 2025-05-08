package co.edu.poli.amazonstore.model;

public interface ManejadorPedido {

    void setSiguiente(ManejadorPedido siguiente);
    void procesar(Pedido pedido);
    
}