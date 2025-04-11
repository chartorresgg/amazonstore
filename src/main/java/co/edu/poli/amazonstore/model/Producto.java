package co.edu.poli.amazonstore.model;

public class Producto {
	private String nombreProducto;
	private Proveedor proveedor;

	public Producto(String nombreProducto, Proveedor proveedor) {
		this.nombreProducto = nombreProducto;
		this.proveedor = proveedor;
	}

	public void mostrarInfo() {
		System.out.println("Producto: " + nombreProducto);
		System.out.println("Proveedor: " + proveedor);
		System.out.println("---------------------------");
	}
}