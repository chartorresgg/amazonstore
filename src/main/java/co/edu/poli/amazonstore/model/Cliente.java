package co.edu.poli.amazonstore.model;

public class Cliente {

    private String nombreCliente;
    private boolean esFrecuente;
    private boolean activo;

    public Cliente(String nombreCliente, boolean esFrecuente) {
        this.nombreCliente = nombreCliente;
        this.esFrecuente = esFrecuente;
    }

    public Cliente(String nombreCliente, boolean esFrecuente, boolean activo) {
        this.nombreCliente = nombreCliente;
        this.esFrecuente = esFrecuente;
        this.activo = activo;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    ///--------Métodos----------//
    

    


    
}