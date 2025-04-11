package co.edu.poli.amazonstore.model;

public class ClienteFacade {

	private InformacionPersonal informacionPersonal;
	private HistorialPedidos historialPedidos;
	private FormasPago formasPago;

	public ClienteFacade(String nombre, String correo, String direccion) {
        this.informacionPersonal = new InformacionPersonal(nombre, correo, direccion);
        this.historialPedidos = new HistorialPedidos();
        this.formasPago = new FormasPago();
    }

	public String actualizarInformacionCliente(String nombre, String correo, String direccion) {
		return informacionPersonal.actualizarInformacion(nombre, correo, direccion);
	}

	public String mostrarInformacionCliente() {
		return informacionPersonal.mostrarInformacion();
	}

	public String mostrarHistorialPedidos() {
		return historialPedidos.mostrarHistorial();
	}

	public String realizarNuevoPedido(String pedido) {
		return historialPedidos.realizarPedido(pedido);
	}

	public String verFormasDePago() {
		return formasPago.visualizarFormasPago();
	}

	public String activarPago(String metodo) {
		return formasPago.activarFormaPago(metodo);
	}

	public String bloquearPago(String metodo) {
		return formasPago.bloquearFormaPago(metodo);
	}
}
