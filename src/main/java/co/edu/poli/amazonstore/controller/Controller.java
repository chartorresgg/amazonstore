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
    private TextArea txtDetallePedido, txtResultado, txtHistorialEstados;
    @FXML
    private Label lblTotal, lblEstado;
    @FXML
    private Button btnAgregarProducto, btnReporte, btnDescuento, 
                    btnNuevoPedido, btnImpuesto, btnAvanzarEstado;

    private List<Product> listaProductos, listaProductosOriginales; // Lista que se manipula y lista para restaurar productos con valores iniciales.
    
    private Order pedido; // Representa el pedido actual del cliente.

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * Inicializa la lista de productos disponibles y desactiva el botón de agregar producto.
     */
    @FXML
    public void initialize() {
        listaProductos = new ArrayList<>();
        listaProductos.add(new Product("Laptop", 2000, 0));
        listaProductos.add(new Product("Mouse", 50, 0));
        listaProductos.add(new Product("Teclado", 80, 0));
        listaProductos.add(new Product("Monitor", 300, 0));
        btnAgregarProducto.setDisable(true); // Desactiva el botón hasta que haya un pedido creado.


    // Agregar productos a la lista de la interfaz
    for (Product p : listaProductos) {
        lvProductos.getItems().add(p.getNameProduct());
    }

    // Crear una copia de productos originales para restauración posterior
    listaProductosOriginales = new ArrayList<>();
    for (Product p : listaProductos) {
        listaProductosOriginales.add(new Product(p.getNameProduct(), p.getPrice(), p.getTaxAmount()));
    }
    }

    /**
     * Método para crear un nuevo pedido.
     * Se activa al presionar el botón "Nuevo Pedido".
     */
    @FXML
    private void nuevoPedido() {
        String nombre = txtNombreCliente.getText(); // Obtener el nombre del cliente
        String correo = txtCorreoCliente.getText(); // Obtener el correo del cliente
        btnAgregarProducto.setDisable(false); // Habilitar el botón de agregar producto.

        // Validar que el nombre y correo no estén vacíos
        if (nombre.isEmpty() || correo.isEmpty()) {
            txtResultado.setText("Debe ingresar el nombre y correo del cliente antes de crear un pedido.");
            return;
        }

        Client cliente = new Client(nombre, correo); // Crear un nuevo cliente con el nombre y el correo.
        pedido = new Order((int) (Math.random() * 10000), cliente); // Crear un nuevo pedido con un ID aleatorio y el cliente.
        txtDetallePedido.clear(); // Limpiar el detalle del pedido.
        lblTotal.setText("Total: $0"); // Inicializar el total a 0
        lblEstado.setText("Estado: " + pedido.obtenerEstado()); // Mostrar el estado inicial del pedido usando el patrón State.

        // Restaurar productos originales
        listaProductos.clear();
        for (Product p : listaProductosOriginales) {
            listaProductos.add(new Product(p.getNameProduct(), p.getPrice(), p.getTaxAmount()));
        }

        txtResultado.setText("Nuevo pedido creado para " + nombre + " (" + correo + ")"); // Muestra la confirmación de creación del pedido.
    }

    /**
     * Método para agregar un producto al pedido.
     * Se activa al presionar el botón "Agregar Producto".
     * Usa el patrón Visitor para agregar el producto al pedido.
     */
    @FXML
    private void agregarProducto() {
        // Verifica si el pedido ha sido creado
        if (pedido == null) {
    
            txtResultado.setText("Primero debe crear un pedido."); // Mensaje de error si no hay pedido
            return;
        }

        int index = lvProductos.getSelectionModel().getSelectedIndex(); // Obtener el índice del producto seleccionado
        
        // Verifica si se ha seleccionado un producto
        if (index >= 0) {
            Product producto = listaProductos.get(index); // Obtener el producto seleccionado
            pedido.addProduct(producto); // Agrega el producto al pedido
            actualizarDetalle(); // Actualiza el detalle del pedido
        }
    }

    @FXML
    private void generarReporte() {
        if (pedido == null)
            return;

        // Generar el reporte utilizando el visitor
        // El visitor recorre el pedido, cliente y productos
        ReportVisitor visitor = new ReportVisitor();
        pedido.accept(visitor); // El pedido, cliente y productos aceptan el visitor

        String reporteConTotal = visitor.obtenerReporte()
        + "\nTotal con impuestos: $" + pedido.getTotal();
        txtResultado.setText(reporteConTotal);
    }

    /**
     * Método para aplicar un descuento del 10% a todos los productos del pedido.
     * Se activa al presionar el botón "Aplicar Descuento".
     * Usa el patrón Visitor para aplicar el descuento a los productos en el pedido.
     */
    @FXML
    private void aplicarDescuento() {

        if (pedido == null)
            return;
        // Aplicar descuento utilizando el visitor
        // El visitor recorre el pedido y aplica el descuento a los productos
        DiscountVisitor descuento = new DiscountVisitor();
        pedido.accept(descuento);

        txtResultado.setText("Descuento del 10% aplicado a todos los productos.");
        actualizarDetalle();
    }

    
    /**
     * Método para calcular el impuesto del pedido.
     * Se activa al presionar el botón "Calcular Impuesto".
     * Usa el patrón Visitor para calcular el impuesto de los productos en el pedido.
     */

    @FXML
    private void calcularImpuesto() {
        if (pedido == null) return; // Verifica si el pedido ha sido creado

        // Calcular impuestos utilizando el visitor
        TaxVisitor taxVisitor = new TaxVisitor();
        pedido.accept(taxVisitor); // El pedido acepta el visitor para calcular impuestos

        double impuesto = taxVisitor.getTotalTax(); // Obtener el total de impuestos calculados
        txtResultado.setText("Total de impuestos (19%): $" + impuesto); // 
        actualizarDetalle(); // Actualiza el detalle del pedido

}

    /**
     * Método para avanzar el estado del pedido.
     * Se activa al presionar el botón "Avanzar Estado".
     * Usa el patrón State para avanzar el estado del pedido.
     */
    @FXML
    private void avanzarEstadoPedido() {

        // Verifica si el pedido ha sido creado
        if (pedido != null) {
            pedido.avanzarEstado(); // Avanza el estado del pedido
            txtResultado.setText("Estado actual: " + pedido.obtenerEstado()); // Muestra el estado actual del pedido
            lblEstado.setText("Estado: " + pedido.obtenerEstado()); // Actualiza el Label también
            actualizarDetalle(); // Actualiza el detalle del pedido
        }
    }

    /**
     * Método para actualizar el detalle del pedido.
     * Muestra los productos, impuestos y total en el TextArea.
     */
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
private void verHistorialEstados() {
    if (pedido == null) {
        txtHistorialEstados.setText("No hay pedido para mostrar historial.");
        return;
    }

    StringBuilder sb = new StringBuilder("Historial de Estados del Pedido:\n");
    int paso = 1;
    for (String estado : pedido.getHistorialEstados()) {
        sb.append(paso++).append(". ").append(estado).append("\n");
    }

    txtHistorialEstados.setText(sb.toString());
}

}