package co.edu.poli.amazonstore.controller;

import co.edu.poli.amazonstore.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtCorreoCliente;
    @FXML
    private ListView<String> lvProductos;
    @FXML
    private TextArea txtDetallePedido, txtResultado;
    @FXML
    private Label lblTotal;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnReporte;
    @FXML
    private Button btnDescuento;
    @FXML
    private Button btnNuevoPedido;

    private List<Product> listaProductos, listaProductosOriginales;
    private Order pedido;

    @FXML
    public void initialize() {
        // Inicializar productos
        listaProductos = new ArrayList<>();
        listaProductos.add(new Product("Laptop", 2000));
        listaProductos.add(new Product("Mouse", 50));
        listaProductos.add(new Product("Teclado", 80));

        // Mostrar productos
        for (Product p : listaProductos) {
            lvProductos.getItems().add(p.getNameProduct());
        }

        // Inicializar copia original de productos ANTES de llamar a nuevoPedido
        listaProductosOriginales = new ArrayList<>();
        for (Product p : listaProductos) {
            listaProductosOriginales.add(new Product(p.getNameProduct(), p.getPrice()));
        }

        nuevoPedido(); // ✅ ahora sí puede usarse
    }

    @FXML
    private void agregarProducto() {
        int index = lvProductos.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Product producto = listaProductos.get(index);
            pedido.agregarProducto(producto);
            actualizarDetalle();
        }
    }

    @FXML
    private void generarReporte() {
        if (pedido == null)
            return;

        ReportVisitor visitor = new ReportVisitor();
        pedido.accept(visitor); // El pedido, cliente y productos aceptan el visitor

        txtResultado.setText(visitor.obtenerReporte());
    }

    @FXML
    private void aplicarDescuento() {
        if (pedido == null)
            return;

        DiscountVisitor descuento = new DiscountVisitor();
        pedido.accept(descuento);

        txtResultado.setText("Descuento del 10% aplicado a todos los productos.");
        actualizarDetalle();
    }

    @FXML
    private void nuevoPedido() {
        Client cliente = new Client(txtNombreCliente.getText(), txtCorreoCliente.getText());
        pedido = new Order((int) (Math.random() * 10000), cliente);
        txtDetallePedido.clear();
        lblTotal.setText("Total: $0");

        listaProductos.clear();
        for (Product p : listaProductosOriginales) {
            listaProductos.add(new Product(p.getNameProduct(), p.getPrice()));
        }
    }

    private void actualizarDetalle() {
        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (Product p : pedido.getProducts()) {
            sb.append(p.getNameProduct()).append(" - $").append(p.getPrice()).append("\n");
            total += p.getPrice();
        }

        txtDetallePedido.setText(sb.toString());
        lblTotal.setText("Total: $" + total);
    }
}