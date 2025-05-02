package co.edu.poli.amazonstore.controller;

import co.edu.poli.amazonstore.model.Caretaker;
import co.edu.poli.amazonstore.model.ClienteFacade;
import co.edu.poli.amazonstore.model.FormasPago;
import co.edu.poli.amazonstore.model.HistorialPedidos;
import co.edu.poli.amazonstore.model.IProducto;
import co.edu.poli.amazonstore.model.InformacionPersonal;
import co.edu.poli.amazonstore.model.Memento;
import co.edu.poli.amazonstore.model.NotificadorPrecio;
import co.edu.poli.amazonstore.model.Producto;
import co.edu.poli.amazonstore.model.ProductoProxy;
import co.edu.poli.amazonstore.model.Proveedor;
import co.edu.poli.amazonstore.model.ProveedorFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

			txt_productflyweight, txt_proveedornombre, txt_telefonoproveedor,

			txt_productname, txt_providername, txt_precioinicial, txt_definirporcentaje, txtAnioRestaurar, txtAnioInicial;

	@FXML
	private Label lbl_infocliente, lbl_nombrecliente, lbl_correocliente, lbl_direccioncliente,

			lbl_tagpedidos, lbl_productonombre, lbl_precioproducto, lbl_tagpagos, lbl_metodopago,

			lbl_nombre, lbl_producto, lbl_autorizacion, lbl_yearpro,

			lbl_titleflyweight, lbl_productoproveedor, lbl_proveedornombre, lbl_telefonoproveedor, lblEstadoProveedor,
			lblTotalProveedores, lbl_selectprod, lbl_year, lbl_selecproduct2, lbl_selecproduct1, lbl_perc;

	@FXML
	private TextArea txtResultado, area_resultado;

	@FXML
	private Button btnMostrar, btnActualizar,

			btn_hacerpedido, btn_mostrarpedido, btn_pagoactivo, btn_pagobloqueado, btn_pagosactivos, btn_mostrar,

			btn_agregarproducto, btn_addprod;

	@FXML
	private ComboBox<String> combo_metodospago;

	@FXML
    private ComboBox<Producto> comboBoxProducto;

	@FXML
    private ComboBox<Producto> comboBoxRestaurarProducto;

	@FXML
    private ComboBox<Producto> comboBoxMostrarProducto;

	@FXML
	private ListView<String> list_fly;

	@FXML
    private ListView<Producto> listViewProductos;

	private ClienteFacade cliente;

	private ObservableList<Producto> productos = FXCollections.observableArrayList();
    private NotificadorPrecio notificador = new NotificadorPrecio();
    private Caretaker caretaker = new Caretaker();

	@FXML
	public void initialize() {

		// Crear instancias de los subsistemas
		InformacionPersonal info = new InformacionPersonal("Juan Pérez", "juan@email.com", "Calle 123");
		HistorialPedidos historial = new HistorialPedidos();
		FormasPago formas = new FormasPago();

		// Inyectar al ClienteFacade con la nueva lógica simplificada
		cliente = new ClienteFacade(info, historial, formas);

		// Configurar elementos de la interfaz gráfica
		btn_agregarproducto.setOnAction(event -> agregarProducto());

		combo_metodospago.getItems().addAll(
			"Efectivo",
			"Tarjeta de crédito",
			"Tarjeta débito",
			"Transferencia PSE",
			"PayPal",
			"Nequi",
			"Puntos Colombia"
		);


		// Memento y Observer

	}

	// FACADE PATTERN IMPLEMENTATION

	// Información personal del cliente

	@FXML
	private void mostrarInformacion() {
		// Solo mostramos la información sin actualizar
		String info = cliente.gestionarInformacionCliente("", "", "");
		txtResultado.setText(info);
	}

	@FXML
	private void actualizarInformacion() {
		String nombre = txtNombre.getText();
		String correo = txtCorreo.getText();
		String direccion = txtDireccion.getText();

		String resultado = cliente.gestionarInformacionCliente(nombre, correo, direccion);
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
		String resultado = cliente.procesarPedido(pedido);
		txtResultado.setText(resultado);

		// Limpiar campos
		txt_nombreproducto.clear();
		txt_precioproducto.clear();
	}

	@FXML
	private void mostrarPedidos() {
		// Para solo mostrar el historial sin nuevo pedido
		String resultado = cliente.procesarPedido(""); // Se puede ajustar para que no agregue nada si string vacío
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

		String resultado = cliente.gestionarMetodoPago(metodo, true);
		txtResultado.setText(resultado);
	}

	@FXML
	private void bloquearFormaPago() {
		String metodo = combo_metodospago.getValue();

		if (metodo == null) {
			txtResultado.setText("⚠️ Selecciona un método de pago para bloquearlo.");
			return;
		}

		String resultado = cliente.gestionarMetodoPago(metodo, false);
		txtResultado.setText(resultado);
	}

	@FXML
	private void verFormasPagoActivas() {
		// Solo visualizar sin activar ni bloquear
		String resultado = cliente.gestionarMetodoPago("", true); // Parámetro puede ignorarse si "" no tiene efecto
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

	// Suponiendo que tienes una instancia de la fábrica:
	private final ProveedorFactory proveedorFactory = new ProveedorFactory();

	@FXML
	private void agregarProducto() {
		String nombreProducto = txt_productflyweight.getText().trim();
		String nombreProveedor = txt_proveedornombre.getText().trim();
		String contactoProveedor = txt_telefonoproveedor.getText().trim();

		if (nombreProducto.isEmpty() || nombreProveedor.isEmpty() || contactoProveedor.isEmpty()) {
			// validación simple
			return;
		}

		// Verificar si es un proveedor nuevo
		boolean esNuevoProveedor = !proveedorFactory.proveedorExiste(nombreProveedor, contactoProveedor);

		// Obtener (o reutilizar) el proveedor
		Proveedor proveedor = proveedorFactory.obtenerProveedor(nombreProveedor, contactoProveedor);
		Producto producto = new Producto(nombreProducto, proveedor, 0.0); // Precio inicial puede ser 0.0 o el que desees

		// Mostrar en la interfaz
		list_fly.getItems().add("Producto: " + nombreProducto + " | Proveedor: " + proveedor.getNombre()
				+ " | Contacto: " + proveedor.getContacto());

		// Mostrar estado del proveedor
		if (esNuevoProveedor) {
			lblEstadoProveedor.setText("✅ Nuevo proveedor registrado.");
		} else {
			lblEstadoProveedor.setText("♻️ Proveedor ya existente reutilizado.");
		}

		// Mostrar total de proveedores únicos
		lblTotalProveedores.setText("Proveedores únicos: " + proveedorFactory.contarProveedores());

		// Limpiar campos
		txt_productflyweight.clear();
		txt_proveedornombre.clear();
		txt_telefonoproveedor.clear();
	}

	// MEMENTO AND OBSERVER PATTERN IMPLEMENTATION

	// Método para agregar un producto
    @FXML
public void addProduct() {
    String nombre = txt_productname.getText();
    String proveedor = txt_providername.getText();
    String precioTexto = txt_precioinicial.getText();
    String anioTexto = txtAnioInicial.getText();

    if (nombre.isEmpty() || proveedor.isEmpty() || precioTexto.isEmpty() || anioTexto.isEmpty()) {
        txtResultado.setText("⚠️ Debes completar todos los campos (nombre, proveedor, precio, año).");
        return;
    }

    double precioInicial;
    int anio;

    try {
        precioInicial = Double.parseDouble(precioTexto);
        anio = Integer.parseInt(anioTexto);
    } catch (NumberFormatException e) {
        txtResultado.setText("⚠️ El precio y el año deben ser números válidos.");
        return;
    }

    Producto producto = new Producto(nombre, new Proveedor(proveedor, "Contacto del proveedor"), precioInicial);
    productos.add(producto);
    notificador.agregarSuscriptor(producto);

    // Guardar el memento con el año proporcionado
    Memento memento = new Memento(precioInicial, anio);
    caretaker.guardarMemento(producto.getNombreProducto(), memento);

    // Actualizar interfaces
    listViewProductos.setItems(productos);
    comboBoxProducto.setItems(productos);
    comboBoxRestaurarProducto.setItems(productos);
    comboBoxMostrarProducto.setItems(productos);
    comboBoxMostrarProducto.getSelectionModel().select(producto);

    txt_productname.clear();
    txt_providername.clear();
    txt_precioinicial.clear();
    txtAnioInicial.clear();

    txtResultado.setText("✅ Producto agregado en el año " + anio + ": " + producto.getNombreProducto() + " con precio $" + producto.getPrecioActual());
}


    // Método para aplicar aumento o baja de precio
    @FXML
public void aplicarAjustePrecio() {
	
    double porcentaje = Double.parseDouble(txt_definirporcentaje.getText());
    int añoActual = java.time.Year.now().getValue();  // Año actual

    for (Producto producto : productos) {
        double precioAntes = producto.getPrecioActual();

        // Guardar el estado antes del ajuste
        Memento memento = new Memento(precioAntes, añoActual);
        caretaker.guardarMemento(producto.getNombreProducto(), memento);
        producto.guardarEstado(añoActual);  // Guardar también en el historial del producto

        // Aplicar el cambio de precio
        producto.actualizarPrecio(porcentaje);

        double precioDespues = producto.getPrecioActual();
        System.out.println("💲 Producto: " + producto.getNombreProducto() +
            " | Año: " + añoActual +
            " | Precio antes: $" + precioAntes +
            " | Precio después: $" + precioDespues +
            " | Cambio: " + (porcentaje > 0 ? "+" : "") + porcentaje + "%");
    }

    txt_definirporcentaje.clear();
	listViewProductos.refresh();
}


    // Método para restaurar el precio de un producto a un año específico
    @FXML
public void restaurarPrecio() {
    Producto productoSeleccionado = comboBoxRestaurarProducto.getValue();
    int anio = Integer.parseInt(txtAnioRestaurar.getText());

    if (productoSeleccionado != null) {
        // Buscar memento en el Caretaker (historial centralizado)
        Memento memento = caretaker.obtenerMemento(productoSeleccionado.getNombreProducto(), anio);
		


        if (memento != null) {
            productoSeleccionado.setPrecioActual(memento.getPrecio()); // Restaurar directamente
            listViewProductos.refresh();
			txtResultado.setText("✅ Precio restaurado a $" + memento.getPrecio() + " del año " + anio);
        } else {
            txtResultado.setText("⚠️ No hay precio guardado para el año " + anio);
        }
    } else {
		
        txtResultado.setText("⚠️ Debes seleccionar un producto para restaurar.");
    }
}


    // Método para mostrar el precio actual del producto
    @FXML
public void mostrarPrecio() {
    System.out.println("Contenido del ComboBox: " + comboBoxMostrarProducto.getItems());

    Producto productoSeleccionado = comboBoxMostrarProducto.getValue();
    System.out.println("Producto seleccionado: " + productoSeleccionado);
	

    if (productoSeleccionado == null) {
        txtResultado.setText("⚠️ Debes seleccionar un producto para ver su precio.");
        return;
    }

    txtResultado.setText("💲 Precio actual de " + productoSeleccionado.getNombreProducto() + ": $" + productoSeleccionado.getPrecioActual());
}


	
}