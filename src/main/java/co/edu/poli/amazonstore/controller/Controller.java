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

	@FXML private ComboBox<String> comboCliente;
    @FXML private Label labelFrecuente;
    @FXML private ListView<String> listViewProductos;
    @FXML private ListView<String> listViewPedido;
    @FXML private ComboBox<String> comboEstrategia;
    @FXML private Button btnAgregar, btnQuitar, btnCalcular;
    @FXML private Label labelBruto, labelDescuento, labelTotal;


    @FXML private CheckBox chkValidarProductos;
    @FXML private CheckBox chkValidarMontoMinimo;
    @FXML private CheckBox chkValidarClienteFrecuente;
    @FXML private TextField txtMontoMinimo;
    @FXML private Button btnValidar;
    @FXML private Label labelEstadoValidacion;
    @FXML private ListView<String> listViewLogValidacion;

	private Map<String, Producto> productosDisponibles = new HashMap<>();
    private Map<String, Cliente> clientes = new HashMap<>();
    private Pedido pedidoActual;

	@FXML
	public void initialize() {

		Cliente ana = new Cliente("Ana", true);
        Cliente juan = new Cliente("Juan", false);
        clientes.put(ana.getNombreCliente(), ana);
        clientes.put(juan.getNombreCliente(), juan);
        comboCliente.getItems().addAll(clientes.keySet());

        // Productos
        productosDisponibles.put("Libro", new Producto("Libro", 100.0));
        productosDisponibles.put("Agenda", new Producto("Agenda", 50.0));
        productosDisponibles.put("Cuaderno", new Producto("Cuaderno", 30.0));
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

	private void seleccionarCliente() {
        String nombre = comboCliente.getValue();
        Cliente cliente = clientes.get(nombre);
        labelFrecuente.setText(cliente.esFrecuente() ? "Sí" : "No");
        pedidoActual = new Pedido(cliente);
        listViewPedido.getItems().clear();
    }

    private void agregarProducto() {
        String seleccionado = listViewProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null && pedidoActual != null) {
            Producto producto = productosDisponibles.get(seleccionado);
            pedidoActual.agregarProducto(producto);
            listViewPedido.getItems().add(seleccionado);
        }
    }

    private void quitarProducto() {
        String seleccionado = listViewPedido.getSelectionModel().getSelectedItem();
        if (seleccionado != null && pedidoActual != null) {
            listViewPedido.getItems().remove(seleccionado);
            pedidoActual.getProductos().removeIf(p -> p.getNombreProducto().equals(seleccionado));
        }
    }

    private void calcularTotal() {
        if (pedidoActual == null) return;

        String estrategia = comboEstrategia.getValue();
        switch (estrategia) {
            case "Cliente Frecuente":
                pedidoActual.establecerEstrategia(new DescuentoClienteFrecuente());
                break;
            case "Promoción":
                pedidoActual.establecerEstrategia(new DescuentoPromocion());
                break;
            case "Sin Descuento":
            default:
                pedidoActual.establecerEstrategia(new SinDescuento());
                break;
        }

        double bruto = pedidoActual.calcularTotalBruto();
        double descuento = pedidoActual.getEstrategia().calcularDescuento(pedidoActual);
        double total = pedidoActual.calcularTotalConDescuento();

        labelBruto.setText(String.format("$ %.2f", bruto));
        labelDescuento.setText(String.format("$ %.2f", descuento));
        labelTotal.setText(String.format("$ %.2f", total));
    }


     public void validarPedido(Pedido pedido) {
    try {
        // Creamos la cadena de validadores
        ManejadorPedido validadorClienteActivo = new ValidadorClienteActivo();
        ManejadorPedido validadorMontoMinimo = new ValidadorMontoMinimo();
        ManejadorPedido validadorStock = new ValidadorStock();

        // Encadenamos los validadores
        validadorClienteActivo.setSiguiente(validadorMontoMinimo);
        validadorMontoMinimo.setSiguiente(validadorStock);

        // Procesamos el pedido pasando por la cadena de validadores
        validadorClienteActivo.procesar(pedido);

        // Si todo es válido, actualizar la UI
        labelEstadoValidacion.setText("Pedido validado exitosamente.");
        System.out.println("Pedido validado exitosamente.");
        
    } catch (RuntimeException e) {
        // Manejo de errores cuando alguna validación falla
        labelEstadoValidacion.setText("Error de validación: " + e.getMessage());
        System.err.println("Error de validación: " + e.getMessage());
    }
}

}