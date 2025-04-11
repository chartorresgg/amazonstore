package co.edu.poli.amazonstore.model;

public class ClienteFacade {

	private InformacionPersonal informacionPersonal;
	private HistorialPedidos historialPedidos;
	private FormasPago formasPago;

	public ClienteFacade(InformacionPersonal informacionPersonal, HistorialPedidos historialPedidos,
			FormasPago formasPago) {
		this.informacionPersonal = informacionPersonal;
		this.historialPedidos = historialPedidos;
		this.formasPago = formasPago;
	}

	
	public String gestionarInformacionCliente(String nombre, String correo, String direccion) {
		
		if (!nombre.isEmpty() && !correo.isEmpty() && !direccion.isEmpty()) {
			
			return informacionPersonal.actualizarInformacion(nombre, correo, direccion);
		} else {
			return informacionPersonal.mostrarInformacion();
		}
	}

	// Método simplificado para procesar un nuevo pedido y devolver el historial
	public String procesarPedido(String pedido) {
		historialPedidos.realizarPedido(pedido);
		return historialPedidos.mostrarHistorial();
	}

	// Método simplificado para gestionar métodos de pago (activar y visualizar)
	public String gestionarMetodoPago(String metodo, boolean activar) {
		if (activar) {
			formasPago.activarFormaPago(metodo);
		} else {
			formasPago.bloquearFormaPago(metodo);
		}
		return formasPago.visualizarFormasPago();
	}
}