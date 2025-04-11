package co.edu.poli.amazonstore.model;

/**
 * Clase que representa un producto real en el sistema.
 * 
 * Esta clase implementa la interfaz Producto y proporciona una implementación
 * concreta para mostrar los detalles del producto.
 */
public class ProductoReal implements Producto {

	private String nombre;
	private String descripcion;

	public ProductoReal(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	/**
	 * Método privado que simula la carga de detalles del producto desde una base de
	 * datos.
	 * 
	 * @return Detalles del producto cargados desde la base de datos.
	 */
	private String cargarDesdeBaseDeDatos() {
		return "📦 Cargando detalles del producto desde la base de datos...\n";
	}

	/**
	 * Método que sobreescribe el método de la interfaz Producto para mostrar los
	 * detalles del producto.
	 * 
	 * @return Detalles del producto.
	 */
	@Override
	public String mostrarDetalles() {

		return cargarDesdeBaseDeDatos() + "🛒 Producto: " + nombre + "\n📄 Descripción: " + descripcion;

	}
}