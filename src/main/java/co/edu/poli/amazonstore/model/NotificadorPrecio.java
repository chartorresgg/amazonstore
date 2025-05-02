package co.edu.poli.amazonstore.model;

import java.util.ArrayList;
import java.util.List;

public class NotificadorPrecio {
    private List<Subscriber> suscriptores = new ArrayList<>();

    public void agregarSuscriptor(Subscriber suscriptor) {
        suscriptores.add(suscriptor);
    }

    public void eliminarSuscriptor(Subscriber suscriptor) {
        suscriptores.remove(suscriptor);
    }

    public void notificarAumento(double porcentaje) {
        for (Subscriber p : suscriptores) {
            p.actualizarPrecio(porcentaje);
        }
    }
}
