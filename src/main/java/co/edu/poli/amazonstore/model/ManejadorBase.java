package co.edu.poli.amazonstore.model;

public abstract class ManejadorBase implements ManejadorPedido {
    protected ManejadorPedido siguiente;

    @Override
    public void setSiguiente(ManejadorPedido siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public void procesar(Pedido pedido) {
        if (siguiente != null) {
            siguiente.procesar(pedido);
        }
    }
}