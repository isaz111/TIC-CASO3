import java.util.Random;

public class Administador extends Thread {
    private int nc;
    private BuzonSemiactivo alertas;
    private MonitorEventos Clasificacion;
    private Random random = new Random();
    
    public Administador(int nc, BuzonSemiactivo alertas, MonitorEventos Clasificacion) {
        this.nc = nc;
        this.alertas = alertas;
        this.Clasificacion = Clasificacion;
    }

    @Override
    public void run() {
        try{
            while(true){
                Evento e = alertas.get();
                if (e.isEsFin()) {
                    for (int i = 0; i < nc; i++) {
                        Evento finEvento = new Evento("Fin", 0, true);
                        Clasificacion.generarEvento(finEvento);
                        break;
                    }
                }
                if (random.nextInt(21)%4==0){
                        Clasificacion.generarEvento(e);
                    }
                }
                
            }
         catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}