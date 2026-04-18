import java.util.LinkedList;
import java.util.Queue;

public class MonitorEventos {
    private Queue<Evento> cola = new LinkedList<>();
    private int capacidad;

    public MonitorEventos(int capacidad) {
        this.capacidad = capacidad;
    }


    public synchronized void generarEvento(Evento e) throws InterruptedException {
        while (cola.size() == capacidad) {
            wait();
        }
        cola.add(e);
        notifyAll(); 
    }


    public synchronized Evento revisarEvento() throws InterruptedException {
        while (cola.isEmpty()) {
            wait(); 
        }
        Evento e = cola.poll();
        notifyAll(); 
        return e;
    }
}