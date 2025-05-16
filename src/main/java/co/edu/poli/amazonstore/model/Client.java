package co.edu.poli.amazonstore.model;

/**
 * Clase Client que representa un cliente en el sistema.
 * Implementa la interfaz ElementStore para permitir la visita de un Visitor.
 * Contiene información sobre el nombre y el correo electrónico del cliente.
 */
public class Client implements ElementStore {

    private String nameClient;
    private String email;

    public Client(String nameClient, String email) {
        this.nameClient = nameClient;
        this.email = email;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método accept que permite a un Visitor visitar este cliente.
     * 
     * @param visitor El Visitor que visita este cliente.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitClient(this);
    }

    @Override
    public String toString() {
        return nameClient + " (" + email + ")";
    }

}