package co.edu.poli.amazonstore.controller;

import co.edu.poli.amazonstore.model.ClienteFacade;
import co.edu.poli.amazonstore.model.IProducto;
import co.edu.poli.amazonstore.model.Producto;
import co.edu.poli.amazonstore.model.ProductoProxy;
import co.edu.poli.amazonstore.model.Proveedor;
import co.edu.poli.amazonstore.model.ProveedorFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private TextField txtNombre, txtCorreo, txtDireccion,

			txt_nombreproducto, txt_precioproducto,

			txt_nombre, txt_descripcion, txt_nivel,

			txt_productflyweight, txt_proveedornombre, txt_telefonoproveedor;

	@FXML
	private Label lbl_infocliente, lbl_nombrecliente, lbl_correocliente, lbl_direccioncliente,

			lbl_tagpedidos, lbl_productonombre, lbl_precioproducto, lbl_tagpagos, lbl_metodopago,

			lbl_nombre, lbl_producto, lbl_autorizacion,

			lbl_titleflyweight, lbl_productoproveedor, lbl_proveedornombre, lbl_telefonoproveedor, lblEstadoProveedor,
			lblTotalProveedores;

	@FXML
	private TextArea txtResultado, area_resultado;

	@FXML
	private Button btnMostrar, btnActualizar,

			btn_hacerpedido, btn_mostrarpedido, btn_pagoactivo, btn_pagobloqueado, btn_pagosactivos, btn_mostrar,

			btn_agregarproducto;

	@FXML
	private ComboBox<String> combo_metodospago;

	@FXML
	private ListView<String> list_fly;

	private ClienteFacade cliente;

	@FXML
	public void initialize() {

		btn_agregarproducto.setOnAction(event -> agregarProducto());
		combo_metodospago.getItems().addAll("Efectivo", "Tarjeta de crédito", "Tarjeta débito", "Transferencia PSE",
				"PayPal", "Nequi", "Puntos Colombia");

		// Cliente inicial con datos por defecto
		cliente = new ClienteFacade("Juan Pérez", "juan@example.com", "Cra 1 #23-45");
	}

	// FACADE PATTERN IMPLEMENTATION

	// Información personal del cliente

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

	// Pedidos del cliente

	@FXML
	private void realizarPedido() {
		String nombreProducto = txt_nombreproducto.getText();
		String precioProducto = txt_precioproducto.getText();

		if (nombreProducto.isEmpty() || precioProducto.isEmpty()) {
			txtResultado.setText("⚠️ Debes ingresar el nombre del producto y su precio.");
			return;
		}

		String pedido = "Producto: " + nombreProducto + ", Precio: $" + precioProducto;
		String resultado = cliente.realizarNuevoPedido(pedido);
		txtResultado.setText(resultado);

		// Limpiar campos después de realizar pedido
		txt_nombreproducto.clear();
		txt_precioproducto.clear();
	}

	@FXML
	private void mostrarPedidos() {
		String resultado = cliente.mostrarHistorialPedidos();
		txtResultado.setText(resultado);
	}

	// Métodos de pago del cliente

	@FXML
	private void activarFormaPago() {
		String metodo = combo_metodospago.getValue();

		if (metodo == null) {
			txtResultado.setText("⚠️ Selecciona un método de pago para activarlo.");
			return;
		}

		String resultado = cliente.activarPago(metodo);
		txtResultado.setText(resultado);
	}

	@FXML
	private void bloquearFormaPago() {
		String metodo = combo_metodospago.getValue();

		if (metodo == null) {
			txtResultado.setText("⚠️ Selecciona un método de pago para bloquearlo.");
			return;
		}

		String resultado = cliente.bloquearPago(metodo);
		txtResultado.setText(resultado);
	}

	@FXML
	private void verFormasPagoActivas() {
		String resultado = cliente.verFormasDePago();
		txtResultado.setText(resultado);
	}

	// PROXY PATTERN IMPLEMENTATION

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

		IProducto producto = new ProductoProxy(nombre, descripcion, nivelAcceso);
		String resultado = producto.mostrarDetalles();
		area_resultado.setText(resultado);
	}

	// FLYWEIGHT PATTERN IMPLEMENTATION

	@FXML
	 private void agregarProducto() {
        String nombreProducto = txt_productflyweight.getText();
        String nombreProveedor = txt_proveedornombre.getText();
        String contactoProveedor = txt_telefonoproveedor.getText();

        if (nombreProducto.isEmpty() || nombreProveedor.isEmpty() || contactoProveedor.isEmpty()) {
            // validación simple
            return;
        }

        Proveedor proveedor = ProveedorFactory.obtenerProveedor(nombreProveedor, contactoProveedor);
        Producto producto = new Producto(nombreProducto, proveedor);

        list_fly.getItems().add("Producto: " + nombreProducto + " | Proveedor: " + proveedor.getNombre());
        
        // limpiar campos
        txt_productflyweight.clear();
        txt_proveedornombre.clear();
        txt_telefonoproveedor.clear();
    }
}
