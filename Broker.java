import java.util.Random;
public class Broker extends Thread {
    private BuzonSemiactivo buzon;
    private MonitorEventos monitor;
    private int numSensores;
    private int numBase_eventos;


    public Broker(Evento evento, BuzonSemiactivo buzon, MonitorEventos monitor, 
        int numSensores, int numBase_eventos){
        this.buzon = buzon;
        this.monitor = monitor;
        this.numSensores= numSensores;
        this.numBase_eventos = numBase_eventos ;
    }

    public int totalEventos(){
        int total = 0;
        for (int i = 1; i <= numSensores; i++) {
            total += numBase_eventos * i;
        }  
        return total;
    }

    public void tieneAnomalia(){
        Random random = new Random();
        try{
            for (int i = 0; i < totalEventos(); i++){
            Evento evento = monitor.revisarEvento();
            int numero = random.nextInt(201);

            if( numero % 8 == 0){
                buzon.put(evento);
            }else{
                monitor.generarEvento(evento);
            }
        }

        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    

    
}
