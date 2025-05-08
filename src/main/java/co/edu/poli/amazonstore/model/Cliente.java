package co.edu.poli.amazonstore.model;

public class Cliente {

    private String nombre;
    private boolean esFrecuente;

    public Cliente(String nombre, boolean esFrecuente) {
        this.nombre = nombre;
        this.esFrecuente = esFrecuente;
    }

    public boolean esFrecuente() {
        return esFrecuente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsFrecuente() {
        return esFrecuente;
    }

    public void setEsFrecuente(boolean esFrecuente) {
        this.esFrecuente = esFrecuente;
    }


    
}