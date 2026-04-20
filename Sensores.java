import java.util.Random;

public class Sensores implements Runnable {
    private int idSensor;
    private int numBase_eventos;
    private int ns;
    private Random random = new Random();
    private BuzonSemiactivo buzonEntrada;

    public Sensores(int numBase_eventos, int idSensor, int numServidores, BuzonSemiactivo buzon) {
        this.numBase_eventos = numBase_eventos;
        this.idSensor = idSensor;
        this.ns = numServidores;
        this.buzonEntrada = buzon;

    }

    @Override
    public void run() {
        int cantidadEventos = numBase_eventos * idSensor;

        for (int i = 1; i <= cantidadEventos; i++) {
            String idEvento = "S"+ idSensor + "-" + i;
            int destino = random.nextInt(ns) + 1;
            System.out.println("Sensor " + idSensor + " generó evento " + idEvento + " -> servidor " + destino);
            Evento evento = new Evento(idEvento, destino, false);
            buzonEntrada.put(evento);
        }
        
    }
        

}
