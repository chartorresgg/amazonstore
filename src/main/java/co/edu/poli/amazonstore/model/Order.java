package co.edu.poli.amazonstore.model;

import java.util.ArrayList;
import java.util.List;

public class Order implements ElementStore {

    private List<Product> products = new ArrayList<>();
    private int id;
    private Client client;

    public Order(int id, Client client) {
        this.id = id;
        this.client = client;
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
            total += p.getPrice();
        }
        return total;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitOrder(this);
    }

}