package co.edu.poli.amazonstore.model;

import java.util.HashMap;
import java.util.Map;

public class Producto implements Subscriber{

	private String nombreProducto;
	private Proveedor proveedor;
	private double precioActual;
    private Map<Integer, Memento> historialPrecios = new HashMap<>();

	public Producto(String nombreProducto, Proveedor proveedor ,double precioInicial) {
		this.nombreProducto = nombreProducto;
		this.proveedor = proveedor;
		this.precioActual = precioInicial;
	}

	public Memento guardarEstado(int año) {
        Memento memento = new Memento(precioActual, año);
        historialPrecios.put(año, memento);
        return memento;
    }

	// MEMENTO - Guardar precio
    public void restaurarEstado(int año) {
        Memento memento = historialPrecios.get(año);
        if (memento != null) {
            this.precioActual = memento.getPrecio();
        }
    }

	 // OBSERVER - Notificación de cambio de precio
	 @Override
	 public void actualizarPrecio(double porcentaje) {
		 double nuevoPrecio = getPrecioActual() * (1 + porcentaje / 100);
		 setPrecioActual(nuevoPrecio);
	 }

	public void mostrarInfo() {
		System.out.println("Producto: " + nombreProducto);
		System.out.println("Proveedor: " + proveedor);
		System.out.println("---------------------------");
	}

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

	@Override
	public String toString() {
    return nombreProducto + " (" + proveedor.getNombre() + ") - $" + precioActual;
	}



}