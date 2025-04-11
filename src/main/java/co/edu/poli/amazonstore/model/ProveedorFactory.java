package co.edu.poli.amazonstore.model;

import java.util.HashMap;
import java.util.Map;

public class ProveedorFactory {

	private final Map<Integer, Proveedor> proveedores;

	public ProveedorFactory() {
		this.proveedores = new HashMap<>();
	}

	public Proveedor obtenerProveedor(String nombre, String contacto) {

		Proveedor proveedorTemp = new Proveedor(nombre.trim(), contacto.trim());
		int clave = proveedorTemp.hashCode();

		if (!proveedores.containsKey(clave)) {
			proveedores.put(clave, proveedorTemp);
			System.out.println("Nuevo proveedor creado.");
		} else {
			System.out.println("Proveedor ya existente reutilizado.");
		}

		return proveedores.get(clave);
	}

	public boolean proveedorExiste(String nombre, String contacto) {
		Proveedor proveedorTemp = new Proveedor(nombre.trim(), contacto.trim());
		return proveedores.containsKey(proveedorTemp.hashCode());
	}

	public int contarProveedores() {
		return proveedores.size();
	}
}