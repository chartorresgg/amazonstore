package co.edu.poli.amazonstore.model;

public class Cliente {

    private String nombreCliente;
    private boolean esFrecuente;

    public Cliente(String nombreCliente, boolean esFrecuente) {
        this.nombreCliente = nombreCliente;
        this.esFrecuente = esFrecuente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public boolean isEsFrecuente() {
        return esFrecuente;
    }

    public boolean esFrecuente() {
        return esFrecuente;
    }    

    public void setEsFrecuente(boolean esFrecuente) {
        this.esFrecuente = esFrecuente;
    }

    ///--------Métodos----------//
    

    


    
}