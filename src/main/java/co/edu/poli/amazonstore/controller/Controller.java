package co.edu.poli.amazonstore.controller;

import java.util.ArrayList;
import java.util.List;

import co.edu.poli.amazonstore.model.Client;
import co.edu.poli.amazonstore.model.DiscountVisitor;
import co.edu.poli.amazonstore.model.Order;
import co.edu.poli.amazonstore.model.Product;
import co.edu.poli.amazonstore.model.ReportVisitor;
import co.edu.poli.amazonstore.model.TaxVisitor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private Button btnNuevoPedido, btnImpuesto;

    private List<Product> listaProductos, listaProductosOriginales;
    private Order pedido;

    @FXML
    public void initialize() {
        listaProductos = new ArrayList<>();
    listaProductos.add(new Product("Laptop", 2000, 0));
    listaProductos.add(new Product("Mouse", 50, 0));
    listaProductos.add(new Product("Teclado", 80, 0));
    btnAgregarProducto.setDisable(true);


     // Mostrar en la lista visual
    for (Product p : listaProductos) {
        lvProductos.getItems().add(p.getNameProduct());
    }

    // Crear una copia de productos originales para restauración posterior
    listaProductosOriginales = new ArrayList<>();
    for (Product p : listaProductos) {
        listaProductosOriginales.add(new Product(p.getNameProduct(), p.getPrice(), p.getTaxAmount()));
    }
    }

    @FXML
    private void agregarProducto() {
        if (pedido == null) {
    
            txtResultado.setText("Primero debe crear un pedido.");
            return;
        }

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

        String reporteConTotal = visitor.obtenerReporte()
    + "\nTOTAL CON IMPUESTOS: $" + pedido.getTotal();
txtResultado.setText(reporteConTotal);
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
        String nombre = txtNombreCliente.getText();
        String correo = txtCorreoCliente.getText();
        btnAgregarProducto.setDisable(false);

        if (nombre.isEmpty() || correo.isEmpty()) {
            txtResultado.setText("Debe ingresar el nombre y correo del cliente antes de crear un pedido.");
            return;
        }

        Client cliente = new Client(nombre, correo);
        pedido = new Order((int) (Math.random() * 10000), cliente);
        txtDetallePedido.clear();
        lblTotal.setText("Total: $0");

        // Restaurar productos originales
        listaProductos.clear();
        for (Product p : listaProductosOriginales) {
            listaProductos.add(new Product(p.getNameProduct(), p.getPrice(), p.getTaxAmount()));
        }

        txtResultado.setText("Nuevo pedido creado para " + nombre + " (" + correo + ")");
    }

    private void actualizarDetalle() {
        StringBuilder sb = new StringBuilder();

        for (Product p : pedido.getProducts()) {
    sb.append(p.getNameProduct())
      .append(" - $").append(p.getPrice())
      .append(" + Impuesto: $").append(p.getTaxAmount())
      .append(" = Total: $").append(p.getPriceWithTax())
      .append("\n");
}

        txtDetallePedido.setText(sb.toString());
        lblTotal.setText("Total: $" + pedido.getTotal());
    }

    @FXML
    private void calcularImpuesto() {
        if (pedido == null) return;

        TaxVisitor taxVisitor = new TaxVisitor();
        pedido.accept(taxVisitor);  // Recorre cliente y productos, suma impuestos

        double impuesto = taxVisitor.getTotalTax();
        txtResultado.setText("Total de impuestos (19%): $" + impuesto);
        actualizarDetalle();

}

}