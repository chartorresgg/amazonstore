package co.edu.poli.amazonstore.model;

/**
 * Clase que representa un cliente en la tienda.
 * Esta clase contiene información sobre el nombre del cliente, si es un cliente frecuente
 *  y si está activo. Esta clase es parte del patrón de diseño Strategy, donde la estrategia de descuento 
 * se puede cambiar en tiempo de ejecución.
 */
public class Cliente {

    private String nombreCliente;
    private boolean esFrecuente;
    private boolean activo;

    /**
     * Método constructor de la clase Cliente
     * @param nombreCliente
     * @param esFrecuente
     * @param activo
     */
    public Cliente(String nombreCliente, boolean esFrecuente, boolean activo) {
        this.nombreCliente = nombreCliente;
        this.esFrecuente = esFrecuente;
        this.activo = activo;
    }

    //========Getters y Setters========//

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
    
}