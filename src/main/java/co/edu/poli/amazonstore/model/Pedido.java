package co.edu.poli.amazonstore.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private List<Producto> productos = new ArrayList<>();
    private Cliente cliente;
    private EstrategiaDescuento estrategia;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void establecerEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double getTotalBruto() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public double calcularTotalConDescuento() {
        double descuento = estrategia.calcularDescuento(this);
        return getTotalBruto() - descuento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public EstrategiaDescuento getEstrategia() {
        return estrategia;
    }


}