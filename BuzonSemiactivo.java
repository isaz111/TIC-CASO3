import java.util.LinkedList;
import java.util.Queue;

public class BuzonSemiactivo {
    private Queue<Evento> cola = new LinkedList<>();

    public synchronized void put(Evento e){
        cola.add(e);
    }

    public Evento get(){
        while (true){
            synchronized (this){
                if (!cola.isEmpty()){
                    return cola.poll();
                }
            }
            Thread.yield();
        }
    }


}
