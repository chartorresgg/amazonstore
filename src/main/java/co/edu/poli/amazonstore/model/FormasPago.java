package co.edu.poli.amazonstore.model;

import java.util.HashMap;
import java.util.Map;

public class FormasPago {

	private Map<String, Boolean> formasPago = new HashMap<>();

	public String visualizarFormasPago() {
		if (formasPago.isEmpty()) {
			return "No hay formas de pago registradas.";
		}

		StringBuilder resultado = new StringBuilder("Formas de pago:\n");
		for (Map.Entry<String, Boolean> entrada : formasPago.entrySet()) {
			String estado = entrada.getValue() ? "Activa" : "Bloqueada";
			resultado.append("- ").append(entrada.getKey()).append(": ").append(estado).append("\n");
		}
		return resultado.toString();
	}

	public String activarFormaPago(String metodo) {
		formasPago.put(metodo, true);
		return "Forma de pago activada: " + metodo;
	}

	public String bloquearFormaPago(String metodo) {
		formasPago.put(metodo, false);
		return "Forma de pago bloqueada: " + metodo;
	}
}
