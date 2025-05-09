package co.edu.poli.amazonstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un pedido con productos y cliente, aplicando lógica de descuento y validación.
 * Contiene una lista de productos, un cliente, una estrategia de descuento y un monto mínimo.
 * Esta clase permite agregar productos, calcular el total bruto y aplicar descuentos según la estrategia definida.
 * Esta clase es parte del patrón de diseño Strategy, donde la estrategia de descuento se puede cambiar en tiempo de ejecución.
 */
public class Pedido {

    private List<Producto> productos = new ArrayList<>();
    private Cliente cliente;
    private EstrategiaDescuento estrategia;
    private double montoMinimo;

    /**
     * Constructor de la clase Pedido.
     * @param cliente El cliente que realiza el pedido.
     */

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Constructor de la clase Pedido.
     * @param cliente El cliente que realiza el pedido.
     * @param montoMinimo El monto mínimo para aplicar un descuento.
     */
    public Pedido(Cliente cliente, double montoMinimo) {
        this.cliente = cliente;
        this.montoMinimo = montoMinimo;
    }

    ///--------Métodos----------//

    /**
     * Agrega un producto a la lista de productos del pedido.
     * @param producto El producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    /**
     * Calcula el total bruto del pedido, es decir, la suma de los precios de todos los productos.
     * @return El total bruto del pedido.
     */
    public double calcularTotalBruto() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    /**
     * Calcula el total del pedido aplicando la estrategia de descuento establecida.
     * @return El total del pedido después de aplicar el descuento.
     */
    public double calcularTotalConDescuento() {
        double descuento = estrategia.calcularDescuento(this);
        return calcularTotalBruto() - descuento;
    }

    /**
     * Establece la estrategia de descuento a utilizar para calcular el total del pedido.
     * @param estrategia La estrategia de descuento a utilizar.
     */
    public void establecerEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    //--------Getters y Setters----------//
    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public EstrategiaDescuento getEstrategia() {
        return estrategia;
    }

    public double getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(double montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

}