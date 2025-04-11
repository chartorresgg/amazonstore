package co.edu.poli.amazonstore.model;
	
import java.util.ArrayList;
import java.util.List;

public class HistorialPedidos {

	private List<String> pedidos = new ArrayList<>();

	public String realizarPedido(String pedido) {
		pedidos.add(pedido);
		return "Pedido realizado: " + pedido;
	}

	public String mostrarHistorial() {
		if (pedidos.isEmpty()) {
			return "No hay pedidos registrados.";
		}

		StringBuilder historial = new StringBuilder("Historial de pedidos:\n");
		for (String pedido : pedidos) {
			historial.append("- ").append(pedido).append("\n");
		}
		return historial.toString();
	}
}
