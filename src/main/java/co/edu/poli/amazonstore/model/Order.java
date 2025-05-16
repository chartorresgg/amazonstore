package co.edu.poli.amazonstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido que puede aceptar visitantes.
 * Implementa la interfaz ElementStore.
 * Contiene una lista de productos, un ID y un cliente asociado al pedido.
 */
public class Order implements ElementStore {

    private List<Product> products = new ArrayList<>();
    private int id;
    private Client client;
    private OrderState state;

    public Order(int id, Client client) {
        this.id = id;
        this.client = client;
        this.state = new EstadoNuevo();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
    public void agregarProducto(Product products) {
        this.products.add(products);
    }

    public double getTotal() {
    double total = 0;
    for (Product p : products) {
        total += p.getPriceWithTax();
    }
    return total;
}


    /**
     * Método para aceptar un visitante.
     * Este método es parte de la implementación del patrón Visitor.
     *
     * @param visitor El visitante que visitará este elemento.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitOrder(this);
    }

    // ============= State Pattern ==============

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }

    public void avanzarEstado() {
        state.avanzarEstado(this);
    }

    public void addProduct(Product product) {
        addProduct(product);
    }

    public String obtenerEstado() {
        return state.obtenerEstado();
    }
}