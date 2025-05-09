package co.edu.poli.amazonstore.controller;

import java.util.HashMap;
import java.util.Map;

import co.edu.poli.amazonstore.model.Cliente;
import co.edu.poli.amazonstore.model.DescuentoClienteFrecuente;
import co.edu.poli.amazonstore.model.DescuentoPromocion;
import co.edu.poli.amazonstore.model.ManejadorPedido;
import co.edu.poli.amazonstore.model.Pedido;
import co.edu.poli.amazonstore.model.Producto;
import co.edu.poli.amazonstore.model.SinDescuento;
import co.edu.poli.amazonstore.model.ValidadorClienteActivo;
import co.edu.poli.amazonstore.model.ValidadorMontoMinimo;
import co.edu.poli.amazonstore.model.ValidadorStock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class Controller {

    // Componentes de Strategy

	@FXML private ComboBox<String> comboCliente;
    @FXML private Label labelFrecuente;
    @FXML private ListView<String> listViewProductos;
    @FXML private ListView<String> listViewPedido;
    @FXML private ComboBox<String> comboEstrategia;
    @FXML private Button btnAgregar, btnQuitar, btnCalcular;
    @FXML private Label labelBruto, labelDescuento, labelTotal;

    // Componentes de Chain of Responsibility

    @FXML private CheckBox chkValidarProductos;
    @FXML private CheckBox chkValidarMontoMinimo;
    @FXML private CheckBox chkValidarClienteActivo;
    @FXML private TextField txtMontoMinimo;
    @FXML private Button btnValidar;
    @FXML private Label labelEstadoValidacion;
    @FXML private ListView<String> listViewLogValidacion;


	private Map<String, Producto> productosDisponibles = new HashMap<>();
    private Map<String, Cliente> clientes = new HashMap<>();
    private Pedido pedidoActual;

	@FXML
	public void initialize() {

		Cliente ana = new Cliente("Ana", true, true);
        Cliente juan = new Cliente("Juan", false, false);
        clientes.put(ana.getNombreCliente(), ana);
        clientes.put(juan.getNombreCliente(), juan);
        comboCliente.getItems().addAll(clientes.keySet());

        // Productos
        productosDisponibles.put("Libro", new Producto("Libro", 100.0, 10));
        productosDisponibles.put("Agenda", new Producto("Agenda", 50.0, 0));
        productosDisponibles.put("Cuaderno", new Producto("Cuaderno", 30.0, 20));
        listViewProductos.getItems().addAll(productosDisponibles.keySet());

        // Estrategias
        comboEstrategia.getItems().addAll("Cliente Frecuente", "Promoción", "Sin Descuento");

        // Eventos
        comboCliente.setOnAction(e -> seleccionarCliente());
        btnAgregar.setOnAction(e -> agregarProducto());
        btnQuitar.setOnAction(e -> quitarProducto());
        btnCalcular.setOnAction(e -> {
            if (pedidoActual != null) {
                validarPedido(pedidoActual);  // Validación previa
                calcularTotal();  // Solo si la validación fue exitosa
            }
        });
        btnValidar.setOnAction(e -> validarPedido(pedidoActual));

	}

    // Métodos de Strategy

    /**
     * Método que se ejecuta al seleccionar un cliente en el ComboBox.
     * Crea un nuevo pedido para el cliente seleccionado y actualiza la etiqueta de cliente frecuente.
     */
	private void seleccionarCliente() {
        String nombre = comboCliente.getValue(); // Obtener el nombre del cliente seleccionado
        Cliente cliente = clientes.get(nombre); // Obtener el objeto Cliente correspondiente
        labelFrecuente.setText(cliente.esFrecuente() ? "Sí" : "No"); // Actualizar la etiqueta de cliente frecuente
        pedidoActual = new Pedido(cliente); // Crear un nuevo pedido para el cliente seleccionado
        listViewPedido.getItems().clear(); 
    }

    private void agregarProducto() {
        String seleccionado = listViewProductos.getSelectionModel().getSelectedItem(); // Obtener el producto seleccionado
        if (seleccionado != null && pedidoActual != null) { // Verificar que haya un producto seleccionado y que el pedido no sea nulo
            Producto producto = productosDisponibles.get(seleccionado); // Obtener el objeto Producto correspondiente
            pedidoActual.agregarProducto(producto); // Agregar el producto al pedido actual
            listViewPedido.getItems().add(seleccionado); // Agregar el nombre del producto a la lista de productos del pedido
        }
    }

    private void quitarProducto() {
        String seleccionado = listViewPedido.getSelectionModel().getSelectedItem();
        if (seleccionado != null && pedidoActual != null) {
            listViewPedido.getItems().remove(seleccionado);
            pedidoActual.getProductos().removeIf(p -> p.getNombreProducto().equals(seleccionado));
        }
    }

    /*
     * Método que calcula el total del pedido actual aplicando la estrategia de descuento seleccionada.
     * Actualiza las etiquetas de bruto, descuento y total con los valores calculados.
     */
    private void calcularTotal() {
        if (pedidoActual == null) return;

        String estrategia = comboEstrategia.getValue(); // Obtener la estrategia seleccionada
        switch (estrategia) {
            case "Cliente Frecuente":
                pedidoActual.establecerEstrategia(new DescuentoClienteFrecuente()); // Aplicar estrategia de descuento para cliente frecuente
                break;
            case "Promoción":
                pedidoActual.establecerEstrategia(new DescuentoPromocion()); // Aplicar estrategia de descuento por promoción
                break;
            case "Sin Descuento":
            default:
                pedidoActual.establecerEstrategia(new SinDescuento()); // No aplicar descuento
                break;
        }

        double bruto = pedidoActual.calcularTotalBruto(); // Calcular el total bruto del pedido
        double descuento = pedidoActual.getEstrategia().calcularDescuento(pedidoActual); // Calcular el descuento aplicado
        double total = pedidoActual.calcularTotalConDescuento(); // Calcular el total después de aplicar el descuento

        labelBruto.setText(String.format("$ %.2f", bruto));
        labelDescuento.setText(String.format("$ %.2f", descuento));
        labelTotal.setText(String.format("$ %.2f", total));
    }

    // Métodos de Chain of Responsibility	

     public void validarPedido(Pedido pedido) {
    listViewLogValidacion.getItems().clear();

    try {
        ManejadorPedido primero = null;
        ManejadorPedido actual = null;

        if (chkValidarClienteActivo.isSelected()) {
            actual = new ValidadorClienteActivo();
            primero = actual;
            listViewLogValidacion.getItems().add("✔ Validador Cliente Activo agregado.");
        }

        if (chkValidarMontoMinimo.isSelected()) {
            if (txtMontoMinimo.getText().isEmpty()) {
                throw new RuntimeException("Debe ingresar el monto mínimo.");
            }
            double montoMin = Double.parseDouble(txtMontoMinimo.getText());
            pedido.setMontoMinimo(montoMin);

            ManejadorPedido nuevo = new ValidadorMontoMinimo();
            if (primero == null) {
                primero = nuevo;
            } else {
                actual.setSiguiente(nuevo);
            }
            actual = nuevo;
            listViewLogValidacion.getItems().add("✔ Validador Monto Mínimo agregado.");
        }

        if (chkValidarProductos.isSelected()) {
            ManejadorPedido nuevo = new ValidadorStock();
            if (primero == null) {
                primero = nuevo;
            } else {
                actual.setSiguiente(nuevo);
            }
            actual = nuevo;
            listViewLogValidacion.getItems().add("✔ Validador Stock agregado.");
        }

        if (primero == null) {
            throw new RuntimeException("Seleccione al menos un validador.");
        }

        primero.procesar(pedido);

        labelEstadoValidacion.setText("✅ Pedido validado exitosamente.");
        listViewLogValidacion.getItems().add("✅ Validación completada con éxito.");
    } catch (RuntimeException e) {
        labelEstadoValidacion.setText("❌ " + e.getMessage());
        listViewLogValidacion.getItems().add("❌ Error: " + e.getMessage());
    }
}


}