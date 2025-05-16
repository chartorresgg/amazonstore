package co.edu.poli.amazonstore.model;

/**
 * Interfaz ElementStore para implementar el patrón Visitor.
 * Define el método accept que permite a los elementos aceptar un Visitor.
 * Las clases que implementen esta interfaz deben definir cómo manejar la visita.
 */
public interface ElementStore {

    void accept(Visitor visitor); // Método para aceptar un Visitor

}