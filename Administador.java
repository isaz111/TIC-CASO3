import java.util.Random;

public class Administador extends Thread {
    private int numClasificadores;
    private BuzonSemiactivo buzonAlertas;
    private MonitorEventos Clasificacion;
    private Random random = new Random();
    
    public Administador(int numClasificadores, BuzonSemiactivo buzonAlertas, MonitorEventos Clasificacion) {
        this.numClasificadores = numClasificadores;
        this.buzonAlertas = buzonAlertas;
        this.Clasificacion = Clasificacion;
    }

    @Override
    public void run() {
        try{
            while(true){
                Evento e = buzonAlertas.get();

                System.out.println("Administrador revisa evento " + e.getId());

                if (e.isEsFin()) {
                    for (int i = 0; i < numClasificadores; i++) {
                        System.out.println("Administrador recibió FIN -> notificando clasificadores");
                        Evento finEvento = new Evento("Fin", 0, true);
                        Clasificacion.generarEvento(finEvento);
                    }
                    return;
                } 
                
                int numRandom = random.nextInt(21);
                if (numRandom % 4 == 0){
                        System.out.println("Evento " + e.getId() + " es SEGURO -> clasificación");
                        Clasificacion.generarEvento(e);
                }else{
                    System.out.println("Evento " + e.getId() + " es MALICIOSO -> descartado");
                }
                    
                }
                
            }
            
         catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}