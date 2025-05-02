package co.edu.poli.amazonstore.model;


import java.util.HashMap;
import java.util.Map;

public class Caretaker {
    
    private Map<String, Map<Integer, Memento>> historial = new HashMap<>();

    public void guardarMemento(String nombreProducto, Memento memento) {
        historial.computeIfAbsent(nombreProducto, k -> new HashMap<>())
                 .put(memento.getAño(), memento);
    }

    public Memento obtenerMemento(String nombreProducto, int año) {
        Map<Integer, Memento> historialProducto = historial.get(nombreProducto);
        if (historialProducto != null) {
            return historialProducto.get(año);
        }
        return null;
    }
}