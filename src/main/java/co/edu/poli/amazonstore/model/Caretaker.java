package co.edu.poli.amazonstore.model;


import java.util.HashMap;
import java.util.Map;

public class Caretaker {
    
    // Mapa para almacenar los mementos de cada producto por año
    // La clave es el nombre del producto y el valor es otro mapa que tiene como clave el año y como valor el memento
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