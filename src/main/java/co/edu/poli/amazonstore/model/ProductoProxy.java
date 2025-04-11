package co.edu.poli.amazonstore.model;

/**
 * Clase que actúa como un proxy para acceder a los detalles de un producto.
 * 
 * Esta clase implementa la interfaz Producto y controla el acceso a la
 * instancia real de ProductoReal, asegurando que solo los usuarios con
 * el nivel de autorización adecuado puedan ver los detalles del producto.
 */
public class ProductoProxy implements Producto {
	
	protected ProductoReal productoReal; // Instancia real del producto
    private String nombre;
    private String descripcion;
    private int nivelUsuario;

    public ProductoProxy(String nombre, String descripcion, int nivelUsuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelUsuario = nivelUsuario;
    }

    /**
	 * Método que controla el acceso a los detalles del producto.
	 * 
	 * @return Detalles del producto o un mensaje de acceso denegado.
	 */
    @Override
    public String mostrarDetalles() {
        if (nivelUsuario >= 1) {
            if (productoReal == null) {
                productoReal = new ProductoReal(nombre, descripcion);
            }
            return productoReal.mostrarDetalles();
        } else {
            return "⛔ Acceso denegado: nivel de autorización insuficiente.";
        }
    }
}
