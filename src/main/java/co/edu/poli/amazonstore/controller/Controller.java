package co.edu.poli.amazonstore.controller;

import co.edu.poli.amazonstore.model.Cliente;
import co.edu.poli.amazonstore.model.Producto;
import co.edu.poli.amazonstore.model.ProductoProxy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private TextField txtNombre, txtCorreo, txtDireccion, txt_nombre, txt_descripcion, txt_nivel;

	@FXML
	private Label lbl_nombre, lbl_producto, lbl_autorizacion;

	@FXML
	private TextArea txtResultado, area_resultado;

	@FXML
	private Button btnMostrar, btnActualizar, btn_mostrar;

	private Cliente cliente;

	@FXML
	public void initialize() {
		// Cliente inicial con datos por defecto
		cliente = new Cliente("Juan Pérez", "juan@example.com", "Cra 1 #23-45");
	}

	@FXML
	private void mostrarInformacion() {
		String info = cliente.mostrarInformacionCliente();
		txtResultado.setText(info);

		// También puedes llenar los campos con la info actual
		// (Esto requiere parsear el texto, pero aquí lo mantenemos simple)
	}

	@FXML
	private void actualizarInformacion() {
		String nombre = txtNombre.getText();
		String correo = txtCorreo.getText();
		String direccion = txtDireccion.getText();

		String resultado = cliente.actualizarInformacionCliente(nombre, correo, direccion);
		txtResultado.setText(resultado);
	}

	// Proxy

	@FXML
	private void verProducto() {
		String nombre = txt_nombre.getText();
		String descripcion = txt_descripcion.getText();

		int nivelAcceso;

		try {
			nivelAcceso = Integer.parseInt(txt_nivel.getText());
		} catch (NumberFormatException e) {
			area_resultado.setText("⚠️ Nivel de autorización inválido. Por favor, ingrese un número entero (0 o 1).");
			return;
		}

		if (nivelAcceso != 0 && nivelAcceso != 1) {
			area_resultado.setText(
					"❌ Nivel de autorización no reconocido. Solo se permiten niveles 0 (sin acceso) y 1 (autorizado).");
			return;
		}

		Producto producto = new ProductoProxy(nombre, descripcion, nivelAcceso);
		String resultado = producto.mostrarDetalles();
		area_resultado.setText(resultado);
	}
}
