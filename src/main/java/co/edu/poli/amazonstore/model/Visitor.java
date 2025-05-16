package co.edu.poli.amazonstore.model;

/**
 * Interfaz Visitor para implementar el patrón Visitor.
 * Define los métodos que deben ser implementados por las clases que deseen
 * recibir visitas de un Visitor.
 */
public interface Visitor {

    void visitClient(Client client); // Método para visitar un cliente

    void visitProduct(Product product); // Método para visitar un producto

    void visitOrder(Order order); // 

}