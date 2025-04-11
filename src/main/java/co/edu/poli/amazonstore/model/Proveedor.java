package co.edu.poli.amazonstore.model;

public class Proveedor {
    private final String nombre;
    private final String contacto;

    public Proveedor(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "nombre='" + nombre + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}