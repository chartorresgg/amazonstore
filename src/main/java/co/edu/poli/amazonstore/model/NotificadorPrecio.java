package co.edu.poli.amazonstore.model;

import java.util.ArrayList;
import java.util.List;

public class NotificadorPrecio {
    private List<Subscriber> suscriptores = new ArrayList<>();

    public void agregarProducto(Subscriber producto) {
        suscriptores.add(producto);
    }

    public void eliminarProducto(Subscriber producto) {
        suscriptores.remove(producto);
    }

    public void notificarAumento(double porcentaje) {
        for (Subscriber p : suscriptores) {
            p.actualizarPrecio(porcentaje);
        }
    }
}
