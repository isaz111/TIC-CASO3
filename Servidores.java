import java.util.Random;

public class Servidores extends Thread {
    private MonitorEventos buzonConsolidacion; 

    public Servidores (MonitorEventos buzonConsolidacion){
        this.buzonConsolidacion = buzonConsolidacion;

    }

    @Override
    public void run() {
        try {
            Random random = new Random();

            while (true) {
                Evento evento = buzonConsolidacion.revisarEvento();

                if (evento.isEsFin()) {
                    return;
                }
                System.out.println("Servidor procesa evento " + evento.getId());
                Thread.sleep(random.nextInt(901) + 100);
                System.out.println("Servidor termina");

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}