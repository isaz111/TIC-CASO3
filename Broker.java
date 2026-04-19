import java.util.Random;
public class Broker extends Thread {
    private BuzonSemiactivo buzonEntrada;
    private BuzonSemiactivo buzonAlertas;
    private MonitorEventos monitor;
    private int numSensores;
    private int numBase_eventos;


    public Broker(BuzonSemiactivo buzonEntrada, BuzonSemiactivo buzonAlertas, MonitorEventos monitor, 
        int numSensores, int numBase_eventos){
        this.buzonEntrada = buzonEntrada;
        this.buzonAlertas = buzonAlertas;
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
            int total = totalEventos();
            for (int i = 0; i < total; i++){
                Evento evento = buzonEntrada.get();
                int numero = random.nextInt(201);

                if( numero % 8 == 0){
                    buzonAlertas.put(evento);
                }else{
                    monitor.generarEvento(evento);
                }
            }

        Evento finEvento = new Evento( "Fin",0 , true);
        buzonAlertas.put(finEvento);

        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        tieneAnomalia();
    }
    
    
}
