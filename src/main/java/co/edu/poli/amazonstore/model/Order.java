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
    private Client client;
    private OrderState state;
    private int id;
    private List<String> historialEstados;

    /**
     * Constructor de la clase Order.
     * 
     * @param id El ID del pedido.
     * @param client El cliente asociado al pedido.
     */
    public Order(int id, Client client) {
        this.id = id;
        this.client = client;
        this.products = new ArrayList<>();
        this.state = new EstadoNuevo();
        this.historialEstados = new ArrayList<>();
        historialEstados.add(state.obtenerEstado());
    }

    //============ Visitor Pattern ==============

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

    /** 
     * Método para agregar un producto al pedido.
     */
    public void addProduct(Product product) {
        state.addProduct(this, product);
    
    }

    /**
     * Método para obtener el total del pedido.
     * Este método calcula el total sumando los precios de todos los productos
     * @return
     */
    public double getTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPriceWithTax();
        }
        return total;
    }

    // ============= State Pattern ==============

    /**
     * Método para avanzar el estado del pedido.
     * Este método utiliza el patrón State para cambiar el estado del pedido.
     */
    public void avanzarEstado() {
        state.avanzarEstado(this);
        historialEstados.add(state.obtenerEstado()); // Guarda el nuevo estado en el historial
    }

    public List<String> getHistorialEstados() {
        return historialEstados;
    }

    /**
     * Método para obtener el estado del pedido.
     * 
     * @return El estado actual del pedido.
     */
    public String obtenerEstado() {
        return state.obtenerEstado();
    }

    //============= Getters y Setters ==============

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}