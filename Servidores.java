import java.util.Random;

public class Servidores extends Thread {
    private int idServidor;
    private MonitorEventos buzonConsolidacion; 

    public Servidores (MonitorEventos buzonConsolidacion, int idServidor){
        this.buzonConsolidacion = buzonConsolidacion;
        this.idServidor = idServidor;


    }

    @Override
    public void run() {
        try {
            Random random = new Random();

            while (true) {
                Evento evento = buzonConsolidacion.revisarEvento();

                if (evento.isEsFin()) {
                     System.out.println("Servidor " + idServidor + " recibió FIN y termina");
                    return;
                }
                System.out.println("Servidor " + idServidor + " procesa evento " + evento.getId());
                Thread.sleep(random.nextInt(901) + 100);
                System.out.println("Servidor " + idServidor + " termina de procesar evento " + evento.getId());

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}