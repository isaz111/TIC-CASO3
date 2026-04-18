import java.util.LinkedList;
import java.util.Queue;

public class MonitorEventos {
    private Queue<Evento> cola = new LinkedList<>();
    private int capacidad;

    public MonitorEventos(int capacidad) {
        this.capacidad = capacidad;
    }

    // Equivale a "put" o depositar un evento
    public synchronized void generarEvento(Evento e) throws InterruptedException {
        while (cola.size() == capacidad) {
            wait(); // Espera pasiva si el buzón está lleno
        }
        cola.add(e);
        notifyAll(); // Despierta a los hilos que estén en wait()
    }

    // Equivale a "get" o retirar un evento
    public synchronized Evento revisarEvento() throws InterruptedException {
        while (cola.isEmpty()) {
            wait(); // Espera pasiva si el buzón está vacío
        }
        Evento e = cola.poll();
        notifyAll(); // Despierta a los hilos que estén en wait()
        return e;
    }
}