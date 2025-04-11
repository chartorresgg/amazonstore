package co.edu.poli.amazonstore.model;

import java.util.Objects;

public class Proveedor {
	private String nombre;
	private String contacto;

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
	public int hashCode() {
		// Usamos versión normalizada del nombre y contacto
		return Objects.hash(normalizar(nombre), normalizar(contacto));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Proveedor))
			return false;
		Proveedor other = (Proveedor) obj;
		return normalizar(this.nombre).equals(normalizar(other.nombre))
				&& normalizar(this.contacto).equals(normalizar(other.contacto));
	}

	private String normalizar(String str) {
		return str == null ? "" : str.trim().toLowerCase();
	}
}
