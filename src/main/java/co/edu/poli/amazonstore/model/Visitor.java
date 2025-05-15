package co.edu.poli.amazonstore.model;

public interface Visitor {

    void visitClient(Client client);

    void visitProduct(Product product);

    void visitOrder(Order order);

}