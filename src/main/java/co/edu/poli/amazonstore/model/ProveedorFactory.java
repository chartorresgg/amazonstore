package co.edu.poli.amazonstore.model;

import java.util.HashMap;
import java.util.Map;

public class ProveedorFactory {
	private static final Map<String, Proveedor> proveedores = new HashMap<>();

	private static String generarClave(String nombre, String contacto) {
		return (nombre.trim() + "_" + contacto.trim()).toLowerCase();
	}

	public static Proveedor obtenerProveedor(String nombre, String contacto) {
		String clave = generarClave(nombre, contacto);
		if (!proveedores.containsKey(clave)) {
			proveedores.put(clave, new Proveedor(nombre.trim(), contacto.trim()));
			System.out.println("Nuevo proveedor creado.");
		} else {
			System.out.println("Proveedor ya existente reutilizado.");
		}
		return proveedores.get(clave);
	}

	public static boolean proveedorExiste(String nombre, String contacto) {
		String clave = generarClave(nombre, contacto);
		return proveedores.containsKey(clave);
	}

	public static int contarProveedores() {
		return proveedores.size();
	}
}