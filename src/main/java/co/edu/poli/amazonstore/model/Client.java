package co.edu.poli.amazonstore.model;

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

    @Override
    public void accept(Visitor visitor) {
        visitor.visitClient(this);
    }

    @Override
    public String toString() {
        return nameClient + " (" + email + ")";
    }

}