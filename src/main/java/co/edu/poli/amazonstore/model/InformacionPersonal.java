package co.edu.poli.amazonstore.model;

public class InformacionPersonal {

	private String nombre;
	private String correo;
	private String direccion;

	public InformacionPersonal(String nombre, String correo, String direccion) {
		this.nombre = nombre;
		this.correo = correo;
		this.direccion = direccion;
	}

	public String actualizarInformacion(String nombre, String correo, String direccion) {
		this.nombre = nombre;
		this.correo = correo;
		this.direccion = direccion;
		return "Información personal actualizada correctamente.";
	}

	public String mostrarInformacion() {
		return "Nombre: " + nombre + "\n" + "Correo: " + correo + "\n" + "Dirección: " + direccion;
	}
}